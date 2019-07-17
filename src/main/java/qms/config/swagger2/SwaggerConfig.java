package qms.config.swagger2;

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
	@Bean
	public Docket controllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
					.title("Application Rest API Doc")
					.description("Application Rest API Doc")
					.contact(new Contact("Feng","","273630150@qq.com"))
					.version("1.0.0")
					.build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("qms.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
