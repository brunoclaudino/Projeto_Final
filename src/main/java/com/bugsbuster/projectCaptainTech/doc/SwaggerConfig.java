package com.bugsbuster.projectCaptainTech.doc;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
     
	private Contact contato() {
		return new Contact(
				"Bluebank - Projeto Final Gama Pan Academy",
				"https://github.com/TML45/Projeto_Final", 
				"jadergreiner@gmail.com"				);
	}
	
	private ApiInfoBuilder informacoesApi() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Buebank - API Rest BackEnd");
		apiInfoBuilder.description("API Rest dos serviços públicos Bluebank");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("https://docs.google.com/document/d/10XcKaj-nUPUFewbr7OkUc2iNcksXOmiapKf9tMgVyGI/edit?usp=sharing");
		apiInfoBuilder.license("Licença - Sua Empresa");
		apiInfoBuilder.licenseUrl("https://docs.google.com/document/d/1FVAUkEsIWBeu9rGmpT7Ht2x-YK9tfza8Qw9d1bRC9QY/edit?usp=sharing");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	
	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
	 
		docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.bugsbuster.projectCaptainTech.controller"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build())
		.consumes(new HashSet<String>(Arrays.asList("application/json")))
		.produces(new HashSet<String>(Arrays.asList("application/json")));
		
		return docket;
	}
}