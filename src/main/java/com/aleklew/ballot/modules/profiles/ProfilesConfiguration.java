package com.aleklew.ballot.modules.profiles;

import com.aleklew.ballot.modules.profiles.interfaces.IAuthenticationService;
import com.aleklew.ballot.modules.profiles.services.BaseAuthenticationService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"com.aleklew.ballot.modules.profiles"})
public class ProfilesConfiguration {

    /*
    @Bean(name = "authenticationService")
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public IAuthenticationService getAuthenticationService() {
        return new BaseAuthenticationService();
    }
    */

}
