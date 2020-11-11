package com.example.test.config;

import com.example.test.bean.Studentes;
import com.example.test.service.StudentService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Studentes.class)
public class HelloAutoConfiguration {
    private final Studentes studentes;


    public HelloAutoConfiguration(Studentes studentes) {
        this.studentes = studentes;
    }
    @Bean
    @ConditionalOnMissingBean
    public StudentService studentServices(){
        return new StudentService(this.studentes.getName(),this.studentes.getSex());
    }
}
