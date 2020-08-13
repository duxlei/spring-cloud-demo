/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller.apollo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 * @date 2020/8/5 23:17
 */
@Getter
@Setter
public class ShopPropBean {
    @Value("${shop.address}")
    private String address;

    @Value("${shop.tel}")
    private String tel;
}
