package tk.ladyka.andrei.utils.spring.impl;

import tk.ladyka.andrei.dto.WebSiteDTO;
import tk.ladyka.andrei.jpa.domain.WebSite;
import tk.ladyka.andrei.jpa.repository.WebSiteRepository;
import tk.ladyka.andrei.utils.converters.WebSiteConverter;
import tk.ladyka.andrei.utils.spring.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    WebSiteRepository webSiteRepository;

    @Autowired
    WebSiteConverter webSiteConverter;

    @Override
    public WebSiteDTO create(WebSiteDTO webSiteDTO) {
        final WebSite webSite = webSiteConverter.toEntity(webSiteDTO);
        final WebSite save = webSiteRepository.saveAndFlush(webSite);
        final WebSiteDTO dto = webSiteConverter.toDTO(save);
        return dto;
    }

    @Override
    public List<WebSiteDTO> get(String query) {
        final List<WebSite> all = webSiteRepository.findAll();
        return webSiteConverter.toDTOs(all);
    }

    @Override
    public WebSiteDTO edit(WebSiteDTO webSiteDTO) {
        final WebSite webSite = webSiteConverter.toEntity(webSiteDTO);
        final WebSite save = webSiteRepository.saveAndFlush(webSite);
        final WebSiteDTO dto = webSiteConverter.toDTO(save);
        return dto;
    }

    @Override
    public boolean delete(Long id) {
        try {
            webSiteRepository.delete(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public WebSiteDTO get(Long id) {
        return webSiteConverter.toDTO(webSiteRepository.findOne(id));
    }
}
