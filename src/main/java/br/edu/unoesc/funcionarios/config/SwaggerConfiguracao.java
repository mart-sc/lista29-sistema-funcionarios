package br.edu.unoesc.funcionarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguracao {
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .apiInfo(apiInfo())
	        .select()
	        .apis(RequestHandlerSelectors.any())
	        .paths(PathSelectors.any())
	        .build();
	}
	
	private ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
	                .title("DEV-TI / Unoesc - API REST de Funcionários")
	                .description("API REST para CRUD de funcionários")
	                .version("1.0.0")
	                .contact(new Contact("Alex Martini", "https://unoesc.edu.br", "alexmartini.sc@gmail.com"))
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	                .build();				
	}
}