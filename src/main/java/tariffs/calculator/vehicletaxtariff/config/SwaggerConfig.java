package tariffs.calculator.vehicletaxtariff.config;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@EnableWebMvc
@Configuration
public class SwaggerConfig {

    private static final Contact DEVELOPER_CONTACT = new Contact("Alan Ramos", "http://antigo.se", "alan.ramos@antigo.se");
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList(APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE));

    /**
     *
     * @return
     */
    @Bean
    public Docket api() {
        //List<SecurityScheme> schemeList = new ArrayList<>();
        //schemeList.add(new BasicAuth("basicAuth"));

        return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.basePackage("tariffs.calculator.vehicletaxtariff.web"))
                   .paths(PathSelectors.any())
                   .build()
                   .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                   .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                   .apiInfo(apiInfo());
                   //.securitySchemes(schemeList);
    }

    /**
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                   .title("Vehicle Tariff Calculator API")
                   .description("Rest services for Vehicle Tariff Calculator API")
                   .contact(DEVELOPER_CONTACT)
                   .license("")
                   .licenseUrl("http://antigo.se")
                   .version("1.0")
                   .build();
    }
}