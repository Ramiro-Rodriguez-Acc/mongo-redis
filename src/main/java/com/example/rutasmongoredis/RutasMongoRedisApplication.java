package com.example.rutasmongoredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RutasMongoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RutasMongoRedisApplication.class, args);
    }

}
