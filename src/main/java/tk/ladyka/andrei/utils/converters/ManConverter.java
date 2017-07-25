package tk.ladyka.andrei.utils.converters;

import tk.ladyka.andrei.dto.ManDTO;
import tk.ladyka.andrei.jpa.domain.Man;
import org.springframework.stereotype.Service;

@Service
public class ManConverter implements AbstractConverter<ManDTO,Man>{
    @Override
    public ManDTO toDTO(Man entity) {
        ManDTO dto = new ManDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setFatherName(entity.getFatherName());
        return dto;
    }

    @Override
    public Man toEntity(ManDTO dto) {
        Man entity = new Man();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFatherName(dto.getFatherName());
        return entity;
    }
}
