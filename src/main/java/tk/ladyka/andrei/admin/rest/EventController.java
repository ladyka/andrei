package tk.ladyka.andrei.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.ladyka.andrei.dto.EventDTO;
import tk.ladyka.andrei.utils.spring.impl.EventService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    EventDTO createOrEdit(Principal principal,Long id,  String description) {
        EventDTO dto = new EventDTO();
        dto.setId(id);
        dto.setDescription(description);
        return eventService.createOrEdit(dto, principal.getName());
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<EventDTO> read(Principal principal, String query) {
        return eventService.get(query, principal.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    EventDTO read(Principal principal, @RequestParam("id") Long id) {
        return eventService.get(id, principal.getName());
    }

    @RequestMapping(value = "/{id}/man/{manId}", method = RequestMethod.GET)
    public
    @ResponseBody
    EventDTO connect(Principal principal, @RequestParam("id") Long id, @RequestParam("manId") Long manId) {
        return eventService.connect(id, manId, principal.getName());
    }
}
