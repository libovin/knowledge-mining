package com.hiekn.knowledge.mining.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogFile;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingInitializationContext;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Set;

//@Service
public class DynamicLoggersConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DynamicLoggersConfig.applicationContext = applicationContext;
    }


    private static final Logger logger = LoggerFactory.getLogger(LoggerConfiguration.class);
    private static final String LOGGER_PATH = "logging.path";
    private static final String LOGGER_TAG = "logging.level.";

    @Autowired
    private LoggingSystem loggingSystem;

    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void onChange(ConfigChangeEvent changeEvent) {
        refreshLoggingLevels();
    }

    @PostConstruct
    private void init() {
        refreshLoggerPath();
    }

    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (containsIgnoreCase(key, LOGGER_TAG)) {
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
                logger.info("{}:{}", key, strLevel);
            }
        }
    }

    private void refreshLoggerPath() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (containsIgnoreCase(key, LOGGER_PATH)) {
                String strPath = config.getProperty(key, "");
                logger.info("{}:{}", key, strPath);
                if (applicationContext instanceof ConfigurableApplicationContext) {
                    ConfigurableEnvironment environment = ((ConfigurableApplicationContext) applicationContext).getEnvironment();
                    String logConfig = environment.resolvePlaceholders("${logging.config:}");
                    LogFile logFile = LogFile.get(environment);
                    loggingSystem.cleanUp();
                    loggingSystem.beforeInitialize();
                    logger.info("{}:{}", logConfig, "new  loggingSystem");
                    loggingSystem.initialize(new LoggingInitializationContext(environment),
                            logConfig, logFile);
                } else {
                    logger.info("{}:{}", "applicationContext", "is not ConfigurableApplicationContext");
                }
            }
        }
        refreshLoggingLevels();
    }


    private static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }
}
