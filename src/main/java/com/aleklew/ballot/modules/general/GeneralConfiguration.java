package com.aleklew.ballot.modules.general;

import com.aleklew.ballot.modules.general.interfaces.BallotService;
import com.aleklew.ballot.modules.general.services.BaseBallotService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan({"com.aleklew.ballot.modules.general"})
public class GeneralConfiguration implements WebMvcConfigurer {

    @Bean(name = "ballotCreatorService")
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public BallotService getAuthenticationService() {
        return new BaseBallotService();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}


