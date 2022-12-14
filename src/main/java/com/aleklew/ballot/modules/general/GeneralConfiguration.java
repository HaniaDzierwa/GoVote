package com.aleklew.ballot.modules.general;

import com.aleklew.ballot.modules.general.interfaces.IBallotCreatorService;
import com.aleklew.ballot.modules.general.services.BaseBallotCreatorService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"com.aleklew.ballot.modules.general"})
public class GeneralConfiguration {

    @Bean(name = "ballotCreatorService")
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public IBallotCreatorService getAuthenticationService() {
        return new BaseBallotCreatorService();
    }

}
