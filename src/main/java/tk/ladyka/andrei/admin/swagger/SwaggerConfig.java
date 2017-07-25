package tk.ladyka.andrei.admin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket securityApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("security")
        .directModelSubstitute(LocalDateTime.class, String.class)
        .apiInfo(new ApiInfoBuilder()
          .title("Admin Tool Security API")
          .description("These API are used to manage the admin tool itself")
          .build())
        .select()
          .paths(regex("/rest/security.*"))
        .build();
  }

  @Bean
  public Docket configuratorApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("configurator")
        .directModelSubstitute(LocalDateTime.class, String.class)
        .apiInfo(new ApiInfoBuilder()
            .title("Configurator API")
            .description("These API are used to manage Configurator data as well as saved user vehicles")
            .build())
        .select()
          .paths(regex("/rest/configurator.*"))
        .build();
  }
}
