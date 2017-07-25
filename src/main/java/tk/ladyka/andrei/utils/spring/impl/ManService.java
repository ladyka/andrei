package tk.ladyka.andrei.utils.spring.impl;

import tk.ladyka.andrei.utils.spring.AbstractRestService;
import tk.ladyka.andrei.dto.ManDTO;
import tk.ladyka.andrei.jpa.domain.Man;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManService extends AbstractRestService<ManDTO,Man> {

    @Override
    public List<ManDTO> get(String query, String userEmail) {
        return converter.toDTOs(baseRepository.findAll());
    }
}
