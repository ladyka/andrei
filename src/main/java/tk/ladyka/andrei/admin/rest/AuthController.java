package tk.ladyka.andrei.admin.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.ladyka.andrei.jpa.domain.sec.Tool;
import tk.ladyka.andrei.jpa.domain.sec.ToolGroup;
import tk.ladyka.andrei.jpa.domain.sec.User;
import tk.ladyka.andrei.jpa.domain.sec.UserAuthority;
import tk.ladyka.andrei.jpa.repository.sec.UserRepository;
import tk.ladyka.andrei.security.UserDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/rest/auth")
@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * This method returns a list of tool group objects, and their associated tool objects, that the currently logged in
     * user has access to.
     *
     * @param authentication Spring supplied Authentication object
     * @return A list of Tool Group objects that the logged in user has access to
     */
    @RequestMapping(value = "/tools", method = RequestMethod.GET)
    public @ResponseBody ToolGroup[] getTools(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepo.findByUsernameIgnoreCase(username);

        List<Long> authorities = new ArrayList<>();

        Set<UserAuthority> userAuthorities = user.getUserAuthorities();
        for (UserAuthority userAuthority : userAuthorities) {
            Long authority = userAuthority.getAuthorityId();
            authorities.add(authority);
        }

        List<Tool> tools = new ArrayList<>();
        for (Long authority : authorities) {
            List<Tool> aTools = Tool.getTools(authority);
            tools.addAll(aTools);
        }

        ToolGroup[] toolGroups = ToolGroup.valuesCopy();
        for (Tool tool : tools) {
            for (ToolGroup toolGroup : toolGroups) {
                if (tool.getToolGroupId() == toolGroup.getId()) {
                    toolGroup.addTool(tool);
                }
            }
        }
        return toolGroups;


//
//        List<Object[]> objects = toolGroupRepo.getAllowableTools(username);
//		HashMap<String, ToolGroup> groupHolder = new HashMap<>();
//		ArrayList<ToolGroup> groups = new ArrayList<>();
//
//		for (Object[] objs : objects) {
//			ToolGroup group = (ToolGroup) objs[0];
//			Tool tool = (Tool) objs[1];
//			if (!groupHolder.containsKey(group.getCode())) {
//				ToolGroup groupJSON = ToolGroup.fromBean(group, false);
//				groupHolder.put(group.getCode(), groupJSON);
//				groups.add(groupJSON);
//			}
//			Tool Tool = Tool.fromBean(tool, true);
//			ToolGroup groupJSON = groupHolder.get(group.getCode());
//			groupJSON.getTools().add(Tool);
//		}
//		return groups;
    }

    /**
     * This method determines if the user has permissions to access a particular tool
     *
     * @param toolCode       The code of the tool to be tested
     * @param authentication Spring supplied Authentication object
     * @return A boolean if the user has permissions to access a particular tool
     */
    @RequestMapping(value = "/hasPermission", method = RequestMethod.GET)
    public Boolean hasPermission(@RequestParam(value = "tool", required = true) String toolCode, Authentication authentication) {
        Tool tool = Tool.findByCode(toolCode);
        if (tool == null) {
            throw new ResourceNotFoundException("No tool with code of " + toolCode);
        }

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return userDetail.hasRole(tool.getAuthority().getCode());
    }

    /**
     * Updates the currently logged in user's password
     *
     * @param authentication Spring supplied Authentication object
     * @param password       Plain text password for the currently logged in user
     * @return A boolean if the password was changed successfully
     */
    @Transactional
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public Boolean updatePassword(@RequestBody String password, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepo.findByUsernameIgnoreCase(userDetails.getUsername());
        if (user == null) {
            throw new ResourceNotFoundException("Could not find user");
        }
        if (StringUtils.isNotEmpty(password)) {
            // make sure the password is encoded properly and not stored in plain text
            user.setPassword(passwordEncoder.encode(password));
            user = userRepo.saveAndFlush(user);
        } else {
            throw new IllegalArgumentException("Password field is empty");
        }
        return true;
    }

}
