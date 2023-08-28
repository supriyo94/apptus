package Apptus.util;

import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import java.util.Properties;

@Configuration
public class VelocityConfig {
   /* @Bean
    public ViewResolver viewResolver() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        // Set up other configuration properties

        // Configure Number Tool
        Properties velocityProperties = new Properties();
        velocityProperties.setProperty("numberTool.locale", "en_US");
        configurer.setVelocityProperties(velocityProperties);

        return configurer;
    }*/

    @Bean
    public NumberTool numberTool() {
        return new NumberTool();
    }
}