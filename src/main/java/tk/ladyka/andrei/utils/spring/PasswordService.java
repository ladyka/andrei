package tk.ladyka.andrei.utils.spring;

import tk.ladyka.andrei.dto.WebSiteDTO;

import java.util.List;

public interface PasswordService {
    WebSiteDTO create(WebSiteDTO webSiteDTO);

    List<WebSiteDTO> get(String query);


    WebSiteDTO edit(WebSiteDTO webSiteDTO);

    boolean delete(Long id);

    WebSiteDTO get(Long id);
}
