package com.example.cafe.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Для Windows: явно указываем абсолютный путь с file:///
        String resourcePath = "file:///" + uploadDir.replace("\\", "/");

        registry.addResourceHandler("/image/uploads/**")
                .addResourceLocations(resourcePath);
    }
}
