package com.hiekn.knowledge.mining;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


//@EnableApolloConfig
@SpringBootApplication
@EnableMongoAuditing
public class KnowledgeMiningApplication {

    private static final Logger log = LoggerFactory.getLogger(KnowledgeMiningApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KnowledgeMiningApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : mutablePropertySources) {
            if (propertySource instanceof CompositePropertySource) {
                CompositePropertySource compositePropertySource = (CompositePropertySource) propertySource;
                log.info("{}", compositePropertySource.toString());
                for (String name : compositePropertySource.getPropertyNames()) {
                    compositePropertySource.getProperty(name);
                    log.info("{}: {} = {}", compositePropertySource.getName(), name, compositePropertySource.getProperty(name));
                }
            }
        }
    }
}

