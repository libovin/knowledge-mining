package com.hiekn.knowledge.mining.config;

import com.hiekn.nlp.tool.support.FdNLPService;
import com.hiekn.nlp.tool.support.HanLpService;
import com.hiekn.nlp.tool.support.LTPService;
import com.hiekn.nlp.tool.support.NLPIRService;
import com.hiekn.nlp.tool.support.StanfordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NlpConfig {

    @Bean
    public FdNLPService fdNLPService() {
        return new FdNLPService();
    }

    @Bean
    public HanLpService hanLpService() {
        return new HanLpService();
    }

    @Bean
    public LTPService ltpService() {
        return new LTPService();
    }

    @Bean
    public NLPIRService nlpirService() {
        return new NLPIRService();
    }

    @Bean
    public StanfordService stanfordService() {
        return new StanfordService();
    }
}
