package com.aleklew.ballot;

import com.aleklew.ballot.modules.general.services.SharedMailingService;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BallotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BallotApplication.class, args);
    }

}
