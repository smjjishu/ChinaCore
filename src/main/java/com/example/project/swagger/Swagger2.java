package com.example.project.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {
    @Bean
    public Docket buildWebDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Web端")
                .apiInfo(buildWebApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.project.controller.web"))
                .build();
    }

    @Bean
    public Docket buildAppDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("App端")
                .apiInfo(buildAPPApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.project.controller.app"))
                .build();
    }

    @Bean
    public Docket buildWebappDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("H5端")
                .apiInfo(buildWebappApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.project.controller.h5"))
                .build();
    }


    @Bean
    public Docket buildCommDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Comm")
                .apiInfo(buildCommApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.project.controller.comm"))
                .build();
    }




    private ApiInfo buildWebApiInfo(){
        return new ApiInfoBuilder()
                .title("网站端")
                .description("网站端接口文档")
                .version("1.0")
                .build();
    }

    private ApiInfo buildAPPApiInfo(){
        return new ApiInfoBuilder()
                .title("移动端")
                .description("移动端接口文档")
                .version("1.0")
                .build();
    }


    private ApiInfo buildWebappApiInfo() {
        return new ApiInfoBuilder()
                .title("H5")
                .description("H5接口文档")
                .version("1.0")
                .build();
    }

    private ApiInfo buildCommApiInfo() {
        return new ApiInfoBuilder()
                .title("Comm")
                .description("Comm接口文档")
                .version("1.0")
                .build();
    }

}