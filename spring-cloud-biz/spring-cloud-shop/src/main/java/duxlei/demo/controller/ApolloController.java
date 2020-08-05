/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller;

import duxlei.demo.config.Prop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/8/2 11:14
 */
@Api(tags = "Apollo配置")
@RestController
@RequestMapping("/apollo")
public class ApolloController {

    @Value("${shop.username}")
    private String timeout;

    @Autowired
    private Prop prop;


    @GetMapping
    @ApiOperation("读取Apollo配置")
    public Object getConfig() {
        return timeout + " : " + prop.getTimeout();
    }


}
