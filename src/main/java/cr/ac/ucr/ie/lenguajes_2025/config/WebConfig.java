package cr.ac.ucr.ie.lenguajes_2025.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Daniel
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configuración existente para recursos estáticos generales
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        
        // Nueva configuración específica para imágenes con recarga automática
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/", "file:src/main/resources/static/images/")
                .setCachePeriod(0) // Sin cache para desarrollo - recarga automática
                .resourceChain(false); // Sin optimizaciones para desarrollo
        
        // Configuración adicional para todos los recursos estáticos con recarga automática
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/", "file:src/main/resources/static/")
                .setCachePeriod(0) // Sin cache para desarrollo
                .resourceChain(false);
<<<<<<< HEAD
        
        // Añadido para imágenes de sorteos
        registry.addResourceHandler("/images/raffle/**")
        .addResourceLocations("file:src/main/resources/static/images/raffle/")
        .setCachePeriod(0)
        .resourceChain(false);
        // Fin añadido
=======
>>>>>>> 84fec5d7b54ea8e0f37c792eeee9c9fb9b359a33
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setPrettyPrint(true);
        converters.add(jsonConverter);
    }
}