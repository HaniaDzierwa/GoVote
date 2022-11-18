package com.aleklew.ballot;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.aleklew.ballot"})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

//        ApplicationContext profilesContext = new AnnotationConfigApplicationContext(ProfilesConfiguration.class);
//        IAuthenticationService authenticationService = profilesContext.getBean("authenticationService", IAuthenticationService.class);
//        try {
//            authenticationService.checkLoginAvailable("aleklew480");
//        } catch (ExecutionControl.NotImplementedException e) {
//            throw new RuntimeException(e);
//        }
    }

}
