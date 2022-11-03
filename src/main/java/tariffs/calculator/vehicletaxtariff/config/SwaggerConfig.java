package tariffs.calculator.vehicletaxtariff.config;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@EnableWebMvc
@Configuration
public class SwaggerConfig {

    private static final String API_TITLE = "Vehicle Tariff Calculator API";
    private static final String API_DESC = "Rest services for Vehicle Tariff Calculator API";
    private static final String API_LIC = "";
    private static final String API_LIC_URL = "http://antigo.se";
    private static final String API_LIC_VERSION = "1.0";
    private static final String DEVELOPER_CONTACT_NAME = "Alan Ramos";
    private static final String DEVELOPER_CONTACT_EMAIL = "alan.ramos@antigo.se";
    private static final String DEVELOPER_CONTACT_URL = "http://antigo.se";
    private static final String APP_BASE_PACKAGE = "tariffs.calculator.vehicletaxtariff.web";
    private static final Contact DEVELOPER_CONTACT = new Contact(DEVELOPER_CONTACT_NAME, DEVELOPER_CONTACT_URL, DEVELOPER_CONTACT_EMAIL);
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList(APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE));

    /**
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.basePackage(APP_BASE_PACKAGE))
                   .paths(PathSelectors.any())
                   .build()
                   .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                   .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                   .apiInfo(apiInfo());
    }

    /**
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                   .title(API_TITLE)
                   .description(API_DESC)
                   .contact(DEVELOPER_CONTACT)
                   .license(API_LIC)
                   .licenseUrl(API_LIC_URL)
                   .version(API_LIC_VERSION)
                   .build();
    }
}