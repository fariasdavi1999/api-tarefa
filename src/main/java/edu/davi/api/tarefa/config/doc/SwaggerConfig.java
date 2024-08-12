package edu.davi.api.tarefa.config.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private Contact contato() {
		return new Contact("Davi Farias",
		                   "https://www.linkedin.com/in/davi-farias-nasc/",
		                   "fariasdavi1999@gmail.com");
	}

	private ApiInfoBuilder apiInfo() {
		ApiInfoBuilder apiInfo = new ApiInfoBuilder();

		apiInfo.title("api-tarefa");
		apiInfo.description("API para criação e gerenciamento de tarefas atreladas à funcionários");
		apiInfo.version("1.0");
		apiInfo.termsOfServiceUrl("Terms of Usage: Open Source");
		apiInfo.licenseUrl("https://www.linkedin.com/in/davi-farias-nasc/");
		apiInfo.contact(contato());

		return apiInfo;
	}

	@Bean
	public Docket detalhetApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select()
		      .apis(RequestHandlerSelectors.basePackage("edu.davi.api.tarefa.controller"))
		      .paths(PathSelectors.any())
		      .build()
		      .apiInfo(this.apiInfo().build())
		      .consumes(new HashSet<>(List.of("application/json")))
		      .produces(new HashSet<>(List.of("application/json")));

		return docket;
	}
}
