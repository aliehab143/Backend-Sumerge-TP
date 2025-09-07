package config;

import implementation.AIRecommender;
import implementation.WebRecommender;
import interfaces.CourseRecommender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"services", "interfaces", "beans"})

public class AppConfig {
    @Bean
    public CourseRecommender aiRecommender() {
        return new AIRecommender();
    }

    @Bean
    public CourseRecommender webRecommender() {
        return new WebRecommender();
    }

}

