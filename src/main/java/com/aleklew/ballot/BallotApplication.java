package com.aleklew.ballot;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.aleklew.ballot"})
@SpringBootApplication
public class BallotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BallotApplication.class, args);
    }

}
