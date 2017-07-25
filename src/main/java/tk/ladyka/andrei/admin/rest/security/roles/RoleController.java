package tk.ladyka.andrei.admin.rest.security.roles;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.ladyka.andrei.jpa.domain.sec.Authority;
import tk.ladyka.andrei.jpa.repository.sec.AuthorityRepository;
import tk.ladyka.andrei.jpa.repository.sec.GroupAuthorityRepository;
import tk.ladyka.andrei.jpa.repository.sec.UserAuthorityRepository;
import tk.ladyka.andrei.json.sec.AuthorityJSON;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC_ROLE')")
@RequestMapping("/rest/security/roles")
@RestController
public class RoleController {

    @Autowired
    private AuthorityRepository authRepo;

    @Autowired
    private UserAuthorityRepository userAuthRepo;

    @Autowired
    private GroupAuthorityRepository groupAuthRepo;

    /**
     * Load all roles available in the DB
     *
     * @param deep Should this method load the child objects
     * @return A list of role objects
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC','ROLE_SEC_ROLE')")
    @RequestMapping(method = RequestMethod.GET)
    public List<AuthorityJSON> getRoles(@RequestParam(value = "deep", required = false, defaultValue = "false") boolean deep) {
        ArrayList<AuthorityJSON> rolesJSON = new ArrayList<>();
        List<Authority> roles = authRepo.findAll();
        for (Authority authority : roles) {
            AuthorityJSON json = AuthorityJSON.fromBean(authority, deep);
            rolesJSON.add(json);
        }
        return rolesJSON;
    }

    /**
     * Get the full details of a role
     *
     * @param roleId The role id to load
     * @return A JSON representation of a role
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public AuthorityJSON getRole(@PathVariable long roleId) {
        Authority role = authRepo.findOne(roleId);
        if (role == null) {
            throw new ResourceNotFoundException("Unable to find Role with id: " + roleId);
        }
        AuthorityJSON json = AuthorityJSON.fromBean(role, true);
        return json;
    }

    /**
     * Create a new role
     *
     * @param json The json object for a role
     * @return The updated role json object
     */
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public AuthorityJSON newRole(@RequestBody AuthorityJSON json) {
        Authority role = new Authority();
        BeanUtils.copyProperties(json, role);
        role = authRepo.saveAndFlush(role);
        AuthorityJSON updatedJson = AuthorityJSON.fromBean(role, false);
        return updatedJson;
    }

    /**
     * Update a particular role
     *
     * @param roleId The roleId of the role to update
     * @param json   The json object for a role
     * @return The updated role json object
     */
    @Transactional
    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
    public AuthorityJSON updateRole(@PathVariable long roleId, @RequestBody AuthorityJSON json) {
        Authority role = authRepo.findOne(roleId);
        if (role == null) {
            throw new ResourceNotFoundException("Unable to find Role with id: " + roleId);
        }
        BeanUtils.copyProperties(json, role);
        role = authRepo.saveAndFlush(role);
        AuthorityJSON updatedJson = AuthorityJSON.fromBean(role, false);
        return updatedJson;
    }

    /**
     * Delete a particular role object
     *
     * @param roleId The role id of the object to delete
     * @return A boolean for success or failure for the delete
     */
    @Transactional
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public boolean deleteRole(@PathVariable long roleId) {
        Authority role = authRepo.findOne(roleId);
        if (role == null) {
            throw new ResourceNotFoundException("Unable to find Role with id: " + roleId);
        }
        // Delete the child objects

        //TODO !!!
        //userAuthRepo.delete(role.getUserAuthorities());
        groupAuthRepo.delete(role.getGroupAuthorities());
        // Then the role object
        authRepo.delete(role);
        return true;
    }

}
