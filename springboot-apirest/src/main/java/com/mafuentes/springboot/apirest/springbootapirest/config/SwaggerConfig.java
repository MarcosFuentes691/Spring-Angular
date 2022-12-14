package com.mafuentes.springboot.apirest.springbootapirest.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.Collections;
import java.util.List;

import static com.mafuentes.springboot.apirest.springbootapirest.constants.SwaggerConstants.*;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;


//@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .securityContexts(singletonList(securityContext()))
                .securitySchemes(singletonList(apiKey())).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex(SECURE_PATH))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(API_TITLE,API_DESCRIPTION,API_VERSION,TERM_OF_SERVICE,contact(),LICENSE,LICENSE_URL, Collections.emptyList());
    }

    private Contact contact(){
        return new Contact(CONTACT_NAME, CONTACT_URL,CONTACT_EMAIL);
    }

    private ApiKey apiKey(){
        return new ApiKey(SECURITY_REFERENCE, AUTHORIZATION, SecurityScheme.In.HEADER.name());
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(securityReference()).build();
    }

    private List<SecurityReference> securityReference(){
        AuthorizationScope[] authorizationScope={
                new AuthorizationScope(AUTHORIZATION_SCOPE, AUTHORIZATION_DESCRIPTION)
        };
        return singletonList(new SecurityReference(SECURITY_REFERENCE,authorizationScope));
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .showCommonExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}