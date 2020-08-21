package com.dr.assignment.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
@ComponentScan("com.dr.assignment.controller")
public class SwaggerConfig {

	private String host;

	private List<String> protocols;

	private String apiTitle;

	private String apiDescription;

	private String apiVersion;

	private String termsOfServiceUrl;

	private String businessOwnerKey;

	private String technicalOwnerKey;

	private String nameKey;

	private String emailKey;

	private String businessOwnerName;

	private String businessOwnerEmail;

	private String technicalOwnerName;

	private String technicalOwnerEmail;

	private String license;

	private String licenseUrl;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).host(host).apiInfo(apiInfo()).protocols(Set.copyOf(protocols))
				.select().apis(RequestHandlerSelectors.basePackage("com.dr.assignment.controller"))
				.paths(PathSelectors.any()).build().pathMapping("/").enableUrlTemplating(false);
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo apiInfo() {
		var vendorExtensions = new ArrayList<VendorExtension>();

		var businessOwner = new ObjectVendorExtension(businessOwnerKey);
		businessOwner.addProperty(new StringVendorExtension(nameKey, businessOwnerName));
		businessOwner.addProperty(new StringVendorExtension(emailKey, businessOwnerEmail));
		vendorExtensions.add(businessOwner);

		var technicalOwner = new ObjectVendorExtension(technicalOwnerKey);
		technicalOwner.addProperty(new StringVendorExtension(nameKey, technicalOwnerName));
		technicalOwner.addProperty(new StringVendorExtension(emailKey, technicalOwnerEmail));
		vendorExtensions.add(technicalOwner);

		return new ApiInfo(apiTitle, apiDescription, apiVersion, termsOfServiceUrl,
				new Contact(technicalOwnerName, "", technicalOwnerEmail), license, licenseUrl, vendorExtensions);
	}
}
