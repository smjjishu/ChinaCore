package com.example.project.filter;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;


/**
 * Web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AllHttpInterceptor httpInterceptor;

    /**
     * 配置拦截器(拦截api开头的地址)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(httpInterceptor).addPathPatterns("/api/**/*").excludePathPatterns("/api/h5/**/*");
    }

    /**
     * 配置跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }

    /**
     配置mybatisplus 分页配置
     * **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        Properties properties = new Properties();
        properties.setProperty("localPage","true");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setProperties(properties);
        return paginationInterceptor;
    }
}
