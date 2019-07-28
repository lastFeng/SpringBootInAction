package com.learn.action.swaggerexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-07-28 11:41
 */
// 开启Swagger2注解
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 配置Swagger2要装载的基础包
     * */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 创建Api基本信息
                .apiInfo(apiInfo())
                .select()
                // 配置基础包，哪些接口需要暴露出来
                // 可以使用@ApiIgnore来排除暴露接口
                .apis(RequestHandlerSelectors.basePackage("com.learn.action.swaggerexample"))
                // 配置需要Swagger2的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API info 信息
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目中使用Swagger2构建RESTful APIs示例")
                .description("Swaggers")
                .version("0.0.1")
                .build();
    }
}
