/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author Administrator
 * @date 2020/8/5 23:16
 */
@Configuration
@EnableApolloConfig
public class PropConfig {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Prop getProp() {
        return new Prop();
    }

    @ApolloConfigChangeListener
    private void configChange(ConfigChangeEvent changeEvent) {
        Set<String> keys = changeEvent.changedKeys();
        for (String key : keys) {
            logger.info("{} : {}", key, changeEvent.getChange(key));
        }
    }

}
