package br.com.pokemon.pokedex_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registro){
        registro.addMapping("/**").allowedOrigins("http://localhost:420","http://localhost:3000").allowedMethods("GET","POST","PUT","DELETE","OPTIONS");
    }
}
