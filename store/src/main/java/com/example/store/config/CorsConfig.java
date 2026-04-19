package com.example.store.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
public class CorsConfig {
private static final String angular_URL ="http://localhost:4200";
private static final String[] allowed_Methods = {"GET",
        "POST",
        "PUT",
        "DELETE",
        "OPTIONS"};

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(angular_URL));
        configuration.setAllowedMethods(List.of(allowed_Methods));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }
 /*
  pour le spring mvc alors que l'autre est pour springSecurity

  @Override
  public void addCorsMappings(CorsRegistry registry){
       registry.addMapping("/**")
               .allowedOrigins(angular_URL)
                     .allowedMethods(allowed_Methods);
            }
        }

*/


}