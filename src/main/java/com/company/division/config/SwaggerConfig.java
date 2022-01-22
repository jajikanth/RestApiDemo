package com.company.division.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
           .apiInfo(apiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.company.division"))              
          .paths(PathSelectors.any())
          .build();
    }
	    


		private ApiInfo apiInfo() {
	        return new ApiInfo(
	                "REST API Demo",
	                "Demo app to demonstrate various features.",
	                "1.0",
	                "Open",
	                new Contact("Jajikanth", "www.example.com", "jaji2015@gmail.com"),
	                "","", Collections.emptyList());
	    }

}
