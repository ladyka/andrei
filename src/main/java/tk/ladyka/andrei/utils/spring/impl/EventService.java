package tk.ladyka.andrei.utils.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tk.ladyka.andrei.dto.EventDTO;
import tk.ladyka.andrei.jpa.domain.Man;
import tk.ladyka.andrei.jpa.repository.EventRepository;
import tk.ladyka.andrei.utils.spring.AbstractRestService;
import tk.ladyka.andrei.jpa.domain.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EventService extends AbstractRestService<EventDTO,Event> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventDTO> get(String query, String userEmail) {
        return converter.toDTOs(baseRepository.findAll());
    }

    public EventDTO connect(Long eventId, Long manId, String userEmail) {
        Event event = eventRepository.getOne(eventId);
        Set<Man> men = event.getMen();
        men.add(new Man(manId));
        event = eventRepository.saveAndFlush(event);
        return converter.toDTO(event);
    }
}
