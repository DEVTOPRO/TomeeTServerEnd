package com.wingstop.tomeet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext())) // Add security context
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wingstop.tomeet"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ToMeet API")
                .description("ToMeet API powered by Wingstop Solutions")
                .version("V1.0")
                .contact(new Contact("Wingstop", "www.google.com", "admin@wingstop.com"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer Token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        AuthorizationScope[] scopes = {new AuthorizationScope("global", "accessEverything")}; // Define the scope
        SecurityReference securityReference = new SecurityReference("Bearer Token", scopes);

        return SecurityContext.builder()
                .securityReferences(Arrays.asList(securityReference))
                .forPaths(PathSelectors.any())
                .build();
    }
}
