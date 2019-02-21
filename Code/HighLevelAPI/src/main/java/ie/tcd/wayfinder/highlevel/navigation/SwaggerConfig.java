package ie.tcd.wayfinder.highlevel.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo())
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();                                           
    }
    
    @Bean
    public ApiInfo apiInfo()
    {
        Contact contact2 = new Contact("Wayfinders", "", "thundyia@tcd.ie");
        return new ApiInfoBuilder().title("Trinity Wayfinders Authentication Module")
                                   .description("Authentication ReST API Documentation")
                                   .contact(contact2)
                                   .version("0.1").build();
}
}
