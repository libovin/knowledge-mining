package com.hiekn.knowledge.mining.config;

import com.hiekn.nlplab.nlptools.NLPToolService;
import com.hiekn.nlplab.nlptools.ToolService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NlpConfig {

    @Bean
    public ToolService toolService(){
        return new NLPToolService();
    }


}
