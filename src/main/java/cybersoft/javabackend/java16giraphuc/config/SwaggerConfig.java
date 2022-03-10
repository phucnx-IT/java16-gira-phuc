package cybersoft.javabackend.java16giraphuc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.RequestHandledEvent;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("cybersoft.javabackend.java16giraphuc"))
						.build().apiInfo(new ApiInfoBuilder().title("Gira Application")
								.version("1.0.0")
								.description("This project is used for education purpose only.")
								.contact(new Contact("Xuan Phuc", "https://www.facebook.com/nguyenxuanphuc.nguyen.1/", "phucnx.phar@gmail.com"))
								.build());
	}
}
