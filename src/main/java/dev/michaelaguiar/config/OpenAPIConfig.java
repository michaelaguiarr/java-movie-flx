package dev.michaelaguiar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nome da sua API")
                        .description("Descrição da sua API")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("seu.email@exemplo.com"))
                        .license(new License()
                                .name("Licença da API")
                                .url("http://url-da-licenca.com")));
    }
}