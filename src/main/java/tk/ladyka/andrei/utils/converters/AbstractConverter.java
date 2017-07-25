package tk.ladyka.andrei.utils.converters;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface AbstractConverter<DTO, Entity> {
    DTO toDTO(Entity entity);

    default List<DTO> toDTOs(List<Entity> list) {
        if ((list == null) || (list.size() == 0))
            return Collections.emptyList();
        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    Entity toEntity(DTO dto);
}



