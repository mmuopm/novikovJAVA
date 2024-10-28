package Proj.library.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
@Bean
    public OpenAPI libraryProject() {
    return new OpenAPI()
            .info(new Info()
                    .title("API библиотеки")
                    .description("Сервис для аренды книг")
                    .version("0.1")
                    .license(new License().name("Apache 2.0"). url("http::/springdoc.org"))
                    .contact(new Contact().name("mmuopm").email("mulyish@gmail.com"))

                    //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
            );
}
}
