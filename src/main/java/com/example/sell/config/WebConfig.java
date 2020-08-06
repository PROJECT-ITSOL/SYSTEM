package com.example.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.addExposedHeader("Authorization");
//        config.setMaxAge(3600L);
//        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
////            log.debug("Registering CORS filter");
//            source.registerCorsConfiguration("/api/**", config);
//        }
//        return new CorsFilter(source);
//    }


//    Config Cors
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowedHeaders("*")
                .allowedOrigins("*").allowedMethods("*")
                .exposedHeaders("Authorization").allowCredentials(false).maxAge(3600);
    }
}
