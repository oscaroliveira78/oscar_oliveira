package br.com.infnet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerSpringDocConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(info())
                .components(new Components());
    }
    
    private Info info() {
        return new Info()
                .title("Infnet - Arquitetura Java")
                .description("Sistema de Gerenciamento de Eventos")
                .version("1.0")
                .license(license())
                .contact(contact());
    }
    
    private Contact contact() {
    	Contact contact = new Contact();
        contact.setName("Oscar Oliveira");
        contact.setUrl("");
        contact.setEmail("oscar.oliveira@al.infnet.edu.br");
        
        return contact;
    }
    
    private License license() {
    	return new License()
                .name("N/A")
                .url("N/A");
    }
}
