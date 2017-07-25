package tk.ladyka.andrei.admin.rest.security.tools;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.ladyka.andrei.jpa.domain.sec.Tool;

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SEC_TOOL')")
@RequestMapping("/rest/security/tools/tool")
@RestController
public class ToolController {

	/**
	 * Loads a tool based on its id
	 *
	 * @param toolId
	 *          The id of the tool to load
	 * @return A JSON of the tool object
	 */
	@RequestMapping(value = "/{toolId}", method = RequestMethod.GET)
	public Tool getTool(@PathVariable long toolId) {
		Tool tool = Tool.findOne(toolId);
		if (tool == null) {
			throw new ResourceNotFoundException("Unable to find Tool with id: " + toolId);
		}
		return tool;
	}
}
