package br.gbank.gbank.config;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import br.gbank.gbank.util.HttpStatusCode;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final String BR_GBANK_GBANK_RESOURCES = "br.gbank.gbank.resources";

    private Map<HttpMethod, List<Response>> getDefaultResponses() {
        Map<HttpMethod, List<Response>> responses = new LinkedHashMap<>();
        responses.put(HttpMethod.GET,
                Arrays.asList(
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.OK)).description(HttpStatusCode.DSC_OK)
                                .build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.NOT_FOUND))
                                .description(String.valueOf(HttpStatusCode.DSC_NOT_FOUND)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.FORBIDDEN))
                                .description(String.valueOf(HttpStatusCode.DSC_FORBIDDEN)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.INTERNAL_ERROR))
                                .description(HttpStatusCode.DSC_INTERNAL_ERROR).build()));

        responses.put(HttpMethod.PUT,
                Arrays.asList(
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.CREATED))
                                .description(String.valueOf(HttpStatusCode.DSC_CREATED)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.NOT_FOUND))
                                .description(String.valueOf(HttpStatusCode.DSC_NOT_FOUND)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.BAD_REQUEST))
                                .description(HttpStatusCode.DSC_BAD_REQUEST).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.FORBIDDEN))
                                .description(String.valueOf(HttpStatusCode.DSC_FORBIDDEN)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.INTERNAL_ERROR))
                                .description(HttpStatusCode.DSC_INTERNAL_ERROR).build()));

        responses.put(HttpMethod.POST,
                Arrays.asList(
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.CREATED))
                                .description(String.valueOf(HttpStatusCode.DSC_CREATED)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.NOT_FOUND))
                                .description(String.valueOf(HttpStatusCode.DSC_NOT_FOUND)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.FORBIDDEN))
                                .description(String.valueOf(HttpStatusCode.DSC_FORBIDDEN)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.INTERNAL_ERROR))
                                .description(HttpStatusCode.DSC_INTERNAL_ERROR).build()));

        responses.put(HttpMethod.DELETE,
                Arrays.asList(
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.NO_CONTENT))
                                .description(String.valueOf(HttpStatusCode.DSC_NO_CONTENT)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.FORBIDDEN))
                                .description(String.valueOf(HttpStatusCode.DSC_FORBIDDEN)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.INTERNAL_ERROR))
                                .description(HttpStatusCode.DSC_INTERNAL_ERROR).build()));

        responses.put(HttpMethod.PATCH,
                Arrays.asList(
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.NO_CONTENT))
                                .description(String.valueOf(HttpStatusCode.DSC_NO_CONTENT)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.FORBIDDEN))
                                .description(String.valueOf(HttpStatusCode.DSC_FORBIDDEN)).build(),
                        new ResponseBuilder().code(String.valueOf(HttpStatusCode.INTERNAL_ERROR))
                                .description(HttpStatusCode.DSC_INTERNAL_ERROR).build()));

        return responses;
    }

    @Bean
    public Docket clienteApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("clientes").useDefaultResponseMessages(false)
                .forCodeGeneration(false).pathMapping("/").tags(new Tag("clientes", "")).select()
                .apis(RequestHandlerSelectors.basePackage(BR_GBANK_GBANK_RESOURCES))
                .paths(PathSelectors.regex("/clientes.*")).build().apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, getDefaultResponses().get(HttpMethod.GET))
                .globalResponses(HttpMethod.POST, getDefaultResponses().get(HttpMethod.POST))
                .globalResponses(HttpMethod.PUT, getDefaultResponses().get(HttpMethod.PUT))
                .globalResponses(HttpMethod.DELETE, getDefaultResponses().get(HttpMethod.DELETE));
    }

    @Bean
    public Docket transferenciaApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("transferencias").useDefaultResponseMessages(false)
                .forCodeGeneration(false).pathMapping("/").tags(new Tag("transferencias", "")).select()
                .apis(RequestHandlerSelectors.basePackage(BR_GBANK_GBANK_RESOURCES))
                .paths(PathSelectors.regex("/transferencias.*")).build().apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, getDefaultResponses().get(HttpMethod.GET))
                .globalResponses(HttpMethod.POST, getDefaultResponses().get(HttpMethod.POST))
                .globalResponses(HttpMethod.PUT, getDefaultResponses().get(HttpMethod.PUT))
                .globalResponses(HttpMethod.DELETE, getDefaultResponses().get(HttpMethod.DELETE));
    }

    @Bean
    public Docket contasApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("contas").useDefaultResponseMessages(false)
                .forCodeGeneration(false).pathMapping("/").tags(new Tag("contas", "")).select()
                .apis(RequestHandlerSelectors.basePackage(BR_GBANK_GBANK_RESOURCES))
                .paths(PathSelectors.regex("/contas.*")).build().apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, getDefaultResponses().get(HttpMethod.GET))
                .globalResponses(HttpMethod.POST, getDefaultResponses().get(HttpMethod.POST))
                .globalResponses(HttpMethod.PUT, getDefaultResponses().get(HttpMethod.PUT))
                .globalResponses(HttpMethod.DELETE, getDefaultResponses().get(HttpMethod.DELETE));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("GBANK REST API")
                .description("Api Rest desenvolvida como projeto Final da GamaAcademy IBM").version("1.0.0")
                .license("MIT License").build();
    }

}
