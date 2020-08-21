package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private Environment env;

    public static final String TAG_DEMO_SERVICE = "Demo Service API";


    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo())
                .tags(
                        new Tag(TAG_DEMO_SERVICE, "Demo service operations"));
    }

    private ApiInfo getApiInfo() {
        String title = env.getProperty("app.service.name")
                .concat(" (")
                .concat(env.getProperty("app.service.owner"))
                .concat(")");

        return new ApiInfo(title,
                env.getProperty("app.description"),
                env.getProperty("app.version"),
                "",
                new Contact(env.getProperty("app.service.contact.name"),
                        env.getProperty("app.service.contact.url"),
                        env.getProperty("app.service.contact.email")),
                "",
                "",
                Collections.emptyList());
    }

}
