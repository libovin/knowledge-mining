package com.hiekn.knowledge.mining;

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

@SpringBootApplication
@EnableMongoAuditing
public class KnowledgeMiningApplication {
    //使用指定的类XXX初始化日志对象，方便在日志输出的时候，可以打印出日志信息所属的类
    private static final Logger log = LoggerFactory.getLogger(KnowledgeMiningApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KnowledgeMiningApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : mutablePropertySources) {
            if (propertySource instanceof CompositePropertySource) {
                CompositePropertySource compositePropertySource = (CompositePropertySource) propertySource;
                //log.info这个应该用来反馈系统的当前状态给最终用户的，所以，在这里输出的信息，应该对最终用户具有实际意义，
                // 也就是最终用户要能够看得明白是什么意思才行。
                //从某种角度上说，Info 输出的信息可以看作是软件产品的一部分（就像那些交互界面上的文字一样），所以需要谨慎对待，不可随便
                log.info("{}", compositePropertySource.toString());
                for (String name : compositePropertySource.getPropertyNames()) {
                    compositePropertySource.getProperty(name);
                    log.info("{}: {} = {}", compositePropertySource.getName(), name, compositePropertySource.getProperty(name));
                }
            }
        }
    }
}

