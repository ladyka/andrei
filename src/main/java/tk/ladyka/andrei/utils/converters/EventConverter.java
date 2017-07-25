package tk.ladyka.andrei.utils.converters;

import tk.ladyka.andrei.dto.EventDTO;
import tk.ladyka.andrei.jpa.domain.Event;
import org.springframework.stereotype.Service;

@Service
public class EventConverter implements AbstractConverter<EventDTO,Event>{
    @Override
    public EventDTO toDTO(Event entity) {
        EventDTO dto = new EventDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTime(entity.getTime());
        return dto;
    }

    @Override
    public Event toEntity(EventDTO dto) {
        Event entity = new Event();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setTime(dto.getTime());
        return entity;
    }
}
