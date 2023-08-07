package com.vincent.assessment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static com.vincent.assessment.util.VendingMachineUtil.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Vending Machine Backend Service",
                version = "1.0.0",
                description = "Backend application to simulate a Vending Machine",
                contact = @Contact(
                        name = "Vincent Mabasa",
                        email = "mabasamv@gmail.com",
                        url = "https://www.linkedin.com/in/makungu-vincent-mabasa/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class OpenApiConfig {

    @Value("${vending-machine.url}")
    private String url;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(servers());
    }

    public List<Server> servers() {
        List<Server> servers = new ArrayList<>();
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Local: vending-machine");
        servers.add(server);
        return servers;
    }
}
