package com.anish.ShoppingSite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ShoppingSiteApplication extends SpringBootServletInitializer{

	public static void main(String args[]) {
		SpringApplication.run(ShoppingSiteApplication.class, args);
	}
	
	@Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
                return application.sources(ShoppingSiteApplication.class);
        }
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.anish.ShoppingSite"))
				.build()
				.apiInfo(apiDetails());
	}

	public ApiInfo apiDetails() {
		List<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
		Contact contact=new Contact(
		         "Anish Prasad",
		         "abc.com", 
				"developer@abc.com");
		
		return new ApiInfo("Shopping Site",
				"An ecommerce App", "0.1","abc.com",contact,"Api License	",
				"abc.com",vendorExtensions);
	}
	
	
	
}
