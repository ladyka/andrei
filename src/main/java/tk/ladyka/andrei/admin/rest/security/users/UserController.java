package tk.ladyka.andrei.admin.rest.security.users;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.ladyka.andrei.jpa.domain.sec.*;
import tk.ladyka.andrei.jpa.repository.sec.GroupUserRepository;
import tk.ladyka.andrei.jpa.repository.sec.UserAuthorityRepository;
import tk.ladyka.andrei.jpa.repository.sec.UserRepository;
import tk.ladyka.andrei.json.sec.UserJSON;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC_USER')")
@RequestMapping("/rest/security/users")
@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private GroupUserRepository groupUserRepo;

    @Autowired
    private UserAuthorityRepository userAuthRepo;

    /**
     * Load all users available in the DB
     *
     * @param deep Should this method load the child objects
     * @return A list of user objects
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC','ROLE_SEC_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserJSON> getUsers(@RequestParam(value = "deep", required = false, defaultValue = "false") boolean deep) {
        ArrayList<UserJSON> usersJSON = new ArrayList<>();
        List<User> users = userRepo.findAll(new Sort("username"));
        for (User user : users) {
            UserJSON json = UserJSON.fromBean(user, deep);
            usersJSON.add(json);
        }
        return usersJSON;
    }

    /**
     * Get the full details of a user
     *
     * @param userId The user id to load
     * @return A JSON representation of a user
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserJSON getUser(@PathVariable long userId) {
        User user = userRepo.findOne(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Unable to find User with id: " + userId);
        }
        UserJSON json = UserJSON.fromBean(user, true);
        return json;
    }

    /**
     * Creates a new User object
     *
     * @param json The json object for a user
     * @return The updated user json object
     */
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public UserJSON newUser(@RequestBody UserJSON json) {
        User user = new User();
        BeanUtils.copyProperties(json, user, "password", "groups", "roles");
        if (StringUtils.isNotEmpty(json.getPassword())) {
            // make sure the password is encoded properly and not stored in plain text
            user.setPassword(passwordEncoder.encode(json.getPassword()));
        }
        user = userRepo.saveAndFlush(user);
        UserJSON updatedJson = UserJSON.fromBean(user, false);
        return updatedJson;
    }

    /**
     * Update a particular user
     *
     * @param userId The userId of the user to update
     * @param json   The json object for a user
     * @return The updated user json object
     */
    @Transactional
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public UserJSON updateUser(@PathVariable long userId, @RequestBody UserJSON json) {
        User user = userRepo.findOne(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Unable to find User with id: " + userId);
        }
        BeanUtils.copyProperties(json, user, "password", "groups", "roles");
        if (StringUtils.isNotEmpty(json.getPassword())) {
            // make sure the password is encoded properly and not stored in plain text
            user.setPassword(passwordEncoder.encode(json.getPassword()));
        }
        user = userRepo.saveAndFlush(user);
        UserJSON updatedJson = UserJSON.fromBean(user, false);
        return updatedJson;
    }

    /**
     * Delete a particular user object
     *
     * @param userId The user id of the object to delete
     * @return A boolean for success or failure for the delete
     */
    @Transactional
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable long userId) {
        User user = userRepo.findOne(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Unable to find User with id: " + userId);
        }
        // Delete the child objects
        groupUserRepo.delete(user.getGroupUsers());
        userAuthRepo.delete(user.getUserAuthorities());
        // then the user object
        userRepo.delete(user);
        return true;
    }

    /**
     * Add a group to a user
     *
     * @param userId  The user id of the object to add a group to
     * @param groupId The group id to add to the user
     * @return A boolean for success or failure for the add
     */
    @Transactional
    @RequestMapping(value = "/{userId}/group", method = RequestMethod.POST)
    public boolean addGroup(@PathVariable long userId, @RequestBody long groupId) {
        List<GroupUser> gus = groupUserRepo.getGroupUser(userId, groupId);
        if (gus == null || gus.isEmpty()) {
            GroupUser groupUser = new GroupUser();

            User user = new User();
            user.setId(userId);
            groupUser.setUser(user);

            Group group = new Group();
            group.setId(groupId);
            groupUser.setGroup(group);

            groupUserRepo.saveAndFlush(groupUser);
        } else {
            throw new IllegalArgumentException("That user already belongs to that group");
        }
        return true;
    }

    /**
     * This method removes a group from the user object
     *
     * @param userId  The user id of the object to delete a group from
     * @param groupId The group id to remove from the user
     * @return A boolean for success or failure for the delete
     */
    @Transactional
    @RequestMapping(value = "/{userId}/group/{groupId}", method = RequestMethod.DELETE)
    public boolean removeGroup(@PathVariable long userId, @PathVariable long groupId) {
        List<GroupUser> gus = groupUserRepo.getGroupUser(userId, groupId);
        if (gus == null || gus.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find Group(" + groupId + ") on User " + userId);
        }

        groupUserRepo.delete(gus.get(0));
        return true;
    }

    /**
     * Adds a role to a user
     *
     * @param userId The userId to add the role to
     * @param roleId The roleId to add to the user
     * @return A boolean for success or failure for the add
     */
    @Transactional
    @RequestMapping(value = "/{userId}/role", method = RequestMethod.POST)
    public boolean addRole(@PathVariable long userId, @RequestBody long roleId) {
        List<UserAuthority> uas = userAuthRepo.getUserAuthority(userId, roleId);
        if (uas == null || uas.isEmpty()) {
            UserAuthority ua = new UserAuthority();

            User user = new User();
            user.setId(userId);
            ua.setUser(user);

            ua.setAuthorityId(roleId);

            userAuthRepo.saveAndFlush(ua);
        } else {
            throw new IllegalArgumentException("That user already has that role");
        }
        return true;
    }

    /**
     * This method removes a role from the user object
     *
     * @param userId The user id of the object to delete a group from
     * @param roleId The role id to remove from the user
     * @return A boolean for success or failure for the delete
     */
    @Transactional
    @RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.DELETE)
    public boolean removeRole(@PathVariable long userId, @PathVariable long roleId) {
        List<UserAuthority> uas = userAuthRepo.getUserAuthority(userId, roleId);
        if (uas == null || uas.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find Role(" + roleId + ") on User " + userId);
        }

        userAuthRepo.delete(uas.get(0));
        return true;
    }

}
