package tk.ladyka.andrei.admin.rest.security.tools;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tk.ladyka.andrei.jpa.domain.sec.ToolGroup;

import java.util.Arrays;
import java.util.List;


@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC_TOOL')")
@RequestMapping("/rest/security/tools/group")
@RestController
public class ToolGroupController {

    /**
     * This returns a list of all tool groups in the system
     *
     * @param deep Boolean to indicate if child objects should be shown
     * @return A list of tool group objects
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC','ROLE_SEC_TOOL')")
    @RequestMapping(method = RequestMethod.GET)
    public List<ToolGroup> getGroups(@RequestParam(value = "deep", required = false, defaultValue = "false") boolean deep) {
        return Arrays.asList(ToolGroup.values());
    }

    /**
     * Returns a deep representation of a tool group and all of its child objects
     *
     * @param toolGroupId The id of the tool group to load
     * @return A tool group object
     */
    @RequestMapping(value = "/{toolGroupId}", method = RequestMethod.GET)
    public ToolGroup getGroup(@PathVariable long toolGroupId) {
        return ToolGroup.findOne(toolGroupId);
    }
}
