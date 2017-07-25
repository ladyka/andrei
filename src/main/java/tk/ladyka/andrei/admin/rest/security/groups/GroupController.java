package tk.ladyka.andrei.admin.rest.security.groups;

import java.util.ArrayList;
import java.util.List;

import tk.ladyka.andrei.json.sec.GroupJSON;
import tk.ladyka.andrei.jpa.domain.sec.Authority;
import tk.ladyka.andrei.jpa.domain.sec.GroupAuthority;
import tk.ladyka.andrei.jpa.domain.sec.GroupUser;
import tk.ladyka.andrei.jpa.domain.sec.User;
import tk.ladyka.andrei.jpa.repository.sec.GroupAuthorityRepository;
import tk.ladyka.andrei.jpa.repository.sec.GroupRepository;
import tk.ladyka.andrei.jpa.repository.sec.GroupUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tk.ladyka.andrei.jpa.domain.sec.Group;

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC_GROUP')")
@RequestMapping("/rest/security/groups")
@RestController
public class GroupController {

	@Autowired
	private GroupRepository groupRepo;

	@Autowired
	private GroupUserRepository groupUserRepo;

	@Autowired
	private GroupAuthorityRepository groupAuthRepo;

	/**
	 * Load all groups available in the DB
	 *
	 * @param deep
	 *          Should this method load the child objects
	 * @return A list of group objects
	 */
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC','ROLE_SEC_GROUP')")
	@RequestMapping(method = RequestMethod.GET)
	public List<GroupJSON> getGroups(@RequestParam(value = "deep", required = false, defaultValue = "false") boolean deep) {
		ArrayList<GroupJSON> groupsJSON = new ArrayList<>();
		List<Group> groups = groupRepo.findAll(new Sort("name"));
		for (Group group : groups) {
			GroupJSON json = GroupJSON.fromBean(group, deep);
			groupsJSON.add(json);
		}
		return groupsJSON;
	}

	/**
	 * Get the full details of a group
	 *
	 * @param groupId
	 *          The group id to load
	 * @return A JSON representation of a group
	 */
	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	public GroupJSON getGroup(@PathVariable long groupId) {
		Group group = groupRepo.findOne(groupId);
		if (group == null) {
			throw new ResourceNotFoundException("Unable to find Group with id: " + groupId);
		}
		GroupJSON json = GroupJSON.fromBean(group, true);
		return json;
	}

	/**
	 * Create a new group
	 *
	 * @param json
	 *          The json object for a group
	 * @return The updated group json object
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public GroupJSON newGroup(@RequestBody GroupJSON json) {
		Group group = new Group();
		BeanUtils.copyProperties(json, group, "users", "roles");
		group = groupRepo.saveAndFlush(group);
		GroupJSON updatedJson = GroupJSON.fromBean(group, false);
		return updatedJson;
	}

	/**
	 * Update a particular group
	 *
	 * @param groupId
	 *          The groupId of the group to update
	 * @param json
	 *          The json object for a group
	 * @return The updated group json object
	 */
	@Transactional
	@RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
	public GroupJSON updateGroup(@PathVariable long groupId, @RequestBody GroupJSON json) {
		Group group = groupRepo.findOne(groupId);
		if (group == null) {
			throw new ResourceNotFoundException("Unable to find Group with id: " + groupId);
		}
		BeanUtils.copyProperties(json, group, "users", "roles");
		group = groupRepo.saveAndFlush(group);
		GroupJSON updatedJson = GroupJSON.fromBean(group, false);
		return updatedJson;
	}

	/**
	 * Delete a particular group object
	 *
	 * @param groupId
	 *          The group id of the object to delete
	 * @return A boolean for success or failure for the delete
	 */
	@Transactional
	@RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
	public boolean deleteGroup(@PathVariable long groupId) {
		Group group = groupRepo.findOne(groupId);
		if (group == null) {
			throw new ResourceNotFoundException("Unable to find Group with id: " + groupId);
		}
		// Delete the child objects
		groupUserRepo.delete(group.getGroupUsers());
		groupAuthRepo.delete(group.getGroupAuthorities());
		// Then the group object
		groupRepo.delete(group);
		return true;
	}

	/**
	 * Add a user to a group
	 *
	 * @param groupId
	 *          The group id of the object to add a user to
	 * @param userId
	 *          The user id to add to the group
	 * @return A boolean for success or failure for the add
	 */
	@Transactional
	@RequestMapping(value = "/{groupId}/user", method = RequestMethod.POST)
	public boolean addUser(@PathVariable long groupId, @RequestBody long userId) {
		List<GroupUser> gus = groupUserRepo.getGroupUser(userId, groupId);
		if (gus == null || gus.isEmpty()) {
			GroupUser groupUser = new GroupUser();

			Group group = new Group();
			group.setId(groupId);
			groupUser.setGroup(group);

			User user = new User();
			user.setId(userId);
			groupUser.setUser(user);

			groupUserRepo.saveAndFlush(groupUser);
		}
		return true;
	}

	/**
	 * This method removes a group from the user object
	 *
	 * @param groupId
	 *          The group id of the object to delete a user from
	 * @param userId
	 *          The user id to remove from the group
	 * @return A boolean for success or failure for the delete
	 */
	@Transactional
	@RequestMapping(value = "/{groupId}/group/{userId}", method = RequestMethod.DELETE)
	public boolean removeUser(@PathVariable long groupId, @PathVariable long userId) {
		List<GroupUser> gus = groupUserRepo.getGroupUser(userId, groupId);
		if (gus == null || gus.isEmpty()) {
			throw new ResourceNotFoundException("Unable to find User(" + userId + ") on Group " + groupId);
		}

		groupUserRepo.delete(gus.get(0));
		return true;
	}

	/**
	 * Adds a role to a group
	 *
	 * @param groupId
	 *          The groupId to add the role to
	 * @param roleId
	 *          The roleId to add to the group
	 * @return A boolean for success or failure for the add
	 */
	@Transactional
	@RequestMapping(value = "/{groupId}/role", method = RequestMethod.POST)
	public boolean addRole(@PathVariable long groupId, @RequestBody long roleId) {
		List<GroupAuthority> gas = groupAuthRepo.getGroupAuthority(groupId, roleId);
		if (gas == null || gas.isEmpty()) {
			GroupAuthority ga = new GroupAuthority();

			Group group = new Group();
			group.setId(groupId);
			ga.setGroup(group);

			Authority auth = new Authority();
			auth.setId(roleId);
			ga.setAuthority(auth);

			groupAuthRepo.saveAndFlush(ga);
		} else {
			throw new IllegalArgumentException("That group already has that role");
		}
		return true;
	}

	/**
	 * This method removes a role from the group object
	 *
	 * @param groupId
	 *          The group id of the object to delete a group from
	 * @param roleId
	 *          The role id to remove from the group
	 * @return A boolean for success or failure for the delete
	 */
	@Transactional
	@RequestMapping(value = "/{groupId}/role/{roleId}", method = RequestMethod.DELETE)
	public boolean removeRole(@PathVariable long groupId, @PathVariable long roleId) {
		List<GroupAuthority> gas = groupAuthRepo.getGroupAuthority(groupId, roleId);
		if (gas == null || gas.isEmpty()) {
			throw new ResourceNotFoundException("Unable to find Role(" + roleId + ") on Group " + groupId);
		}

		groupAuthRepo.delete(gas.get(0));
		return true;
	}

}
