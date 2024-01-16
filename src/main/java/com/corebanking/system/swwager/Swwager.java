package com.corebanking.system.swwager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.annotations.OpenAPI30;

@OpenAPIDefinition(
        info = @Info(
                title = "Core Banking Api",
                description = "Doing Crud operation",
                summary = "This Account-Api will add,delete,update and read",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "I-Consult",
                        email = "help@Iconsult.com.pk"
                ),
                license = @License(
                        name = "N-123456789"
                ),
                version = "V1"
        ),
        servers = {
                @Server(
                        description = "Core-Banking",
                        url = "http://localhost:8081"

                )

        }
)
public class Swwager{

}
