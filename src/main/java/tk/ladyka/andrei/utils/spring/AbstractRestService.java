package tk.ladyka.andrei.utils.spring;

import tk.ladyka.andrei.jpa.repository.BaseRepository;
import tk.ladyka.andrei.utils.converters.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractRestService<DTO, Entity> {

    @Autowired
    public AbstractConverter<DTO, Entity> converter;

    @Autowired
    public BaseRepository<Entity> baseRepository;

    public DTO createOrEdit(DTO dto, String userEmail) {
        Entity entity = converter.toEntity(dto);
        entity = baseRepository.save(entity);
        return converter.toDTO(entity);
    }

    public DTO get(Long id, String userEmail) {
        return converter.toDTO(baseRepository.findOne(id));
    }

    public abstract List<DTO> get(String query, String userEmail);
}
