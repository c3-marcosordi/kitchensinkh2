package org.jboss.as.quickstarts.kitchensink.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class JsfConfiguration {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");  // Adjust this path as needed
        resolver.setSuffix(".jsf");
        System.out.println("ViewResolver bean created"); // Add this line for debugging
        return resolver;
    }
}