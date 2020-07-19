package com.juzi.oerp.configuration;

import cn.hutool.core.collection.CollectionUtil;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Juzi
 * @date 2020/7/19 01:06
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    @Bean
    public Docket userDocket() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("用户端")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.juzi.oerp.controller.user"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket adminDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("管理端")
                .produces(CollectionUtil.newHashSet("application/json"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.juzi.oerp.controller.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket authDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("认证接口")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.juzi.oerp.controller"))
                .paths(PathSelectors.ant("/auth/**/*"))
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        Contact contact = new Contact("桔子", "https://juzibiji.top", "juzi214032@qq.com");
        return new ApiInfoBuilder()
                .title("OERP 在线考试报名平台 API 文档")
                .description("OERP 在线考试报名平台 API 文档")
                .termsOfServiceUrl("https://juzi214032.github.io/OERP-docs")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
