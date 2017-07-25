package tk.ladyka.andrei.utils.converters;

import tk.ladyka.andrei.dto.WebSiteDTO;
import tk.ladyka.andrei.jpa.domain.WebSite;
import org.springframework.stereotype.Service;

@Service
public class WebSiteConverter implements AbstractConverter<WebSiteDTO,WebSite> {

    @Override
    public WebSiteDTO toDTO(WebSite webSite) {
        return new WebSiteDTO(webSite.getId(), webSite.getChangeTime(), webSite.getEmail(),
                webSite.getLogin(), webSite.getMyPassword(), webSite.getOtherInfo(), webSite.getSiteUrl());
    }

    @Override
    public WebSite toEntity(WebSiteDTO dto) {
        return new WebSite(dto.getId(), dto.getChangeTime(), dto.getEmail(),
                dto.getLogin(), dto.getMyPassword(), dto.getOtherInfo(), dto.getSiteUrl());
    }
}
