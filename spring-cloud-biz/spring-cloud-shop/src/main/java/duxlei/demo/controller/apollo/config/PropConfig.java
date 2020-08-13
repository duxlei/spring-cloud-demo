/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller.apollo.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.Set;

/**
 * @author Administrator
 * @date 2020/8/5 23:16
 */
@Slf4j
@Configuration
@EnableApolloConfig(value = {"MD.application-temp-test-other"}, order = Ordered.HIGHEST_PRECEDENCE)
public class PropConfig {


    @Autowired
    private org.springframework.cloud.context.scope.refresh.RefreshScope refreshScope;

    @Bean
    public ShopPropBean getShopPropBean() {
        return new ShopPropBean();
    }

    @Bean("shopConfigBean")
    @RefreshScope
    public ShopConfigPropBean getShopConfigPropBean() {
        return new ShopConfigPropBean();
    }

    @ApolloConfigChangeListener(value = {"MD.application-temp-test", "application"})
    public void configChange(ConfigChangeEvent changeEvent) {
        Set<String> keys = changeEvent.changedKeys();
        for (String key : keys) {
            log.info("{} : {}", key, changeEvent.getChange(key));
        }
        refreshScope.refresh("shopConfigBean");
    }

}
