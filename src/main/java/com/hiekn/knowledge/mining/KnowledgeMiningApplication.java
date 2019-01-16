package com.hiekn.knowledge.mining;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.client.RestTemplate;

@EnableApolloConfig
@SpringBootApplication
@EnableMongoAuditing
public class KnowledgeMiningApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeMiningApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

