/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import duxlei.demo.controller.apollo.config.ShopConfigPropBean;
import duxlei.demo.controller.apollo.config.ShopCusBean;
import duxlei.demo.controller.apollo.config.ShopPropBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * @date 2020/8/2 11:14
 */
@Api(tags = "Apollo配置")
@RestController
@RequestMapping("/apollo")
public class ApolloController {

    @Autowired
    private ShopPropBean shopPropBean;

    @Autowired
    private ShopConfigPropBean shopConfigPropBean;

//    @ApolloConfig("MD.application-temp-test")
    @ApolloConfig("MD.application-temp-public")
    private Config config;

    @ApolloJsonValue("${shop.cusList:[]}")
    private List<ShopCusBean> shopCusBean;

    @Value("${shop.title}")
    private String shopTitle;

    @Value("${shop.income}")
    private Integer shopIncome;



    /**
     * PlaceHolder 使用方式
     * @return
     */
    @GetMapping("/0-place-holder")
    @ApiOperation("配置：PlaceHolder")
    public Object getPlaceHolder() {
        return shopPropBean.getAddress() + " : " + shopPropBean.getTel();
    }


    /**
     * Spring ConfigurationProperties使用方式
     * @return
     */
    @GetMapping("/1-configuration-properties")
    @ApiOperation("配置：Spring ConfigurationProperties")
    public Object getConfig() {
        return shopConfigPropBean.getUsername() + " : " + shopConfigPropBean.getAge();
    }


    /**
     * 使用 @ApolloConfig 注解方式
     * @return
     */
    @GetMapping("/2-annotation-apollo-config")
    @ApiOperation("配置：@ApolloConfig")
    public Object getApolloConfig() {
        Set<String> propertyNames = config.getPropertyNames();
        StringBuilder sb = new StringBuilder();
        for (String name : propertyNames) {
            sb.append(name).append("\t").append(": ").append(config.getProperty(name, "")).append("\n");
        }
        return sb.toString();
    }


    /**
     * 使用 @ApolloJsonValue 注解方式
     * @return
     */
    @GetMapping("/3-annotation-apollo-json-value")
    @ApiOperation("配置：@ApolloJsonValue")
    public Object getApolloJsonValue() {
        return shopCusBean;
    }


    /**
     * 使用 关联 命名空间
     * @return
     */
    @GetMapping("/4-relation-namespace")
    @ApiOperation("配置：关联namespace")
    public Object getRelationNamespace() {
        return shopTitle + " : " + shopIncome;
//        return shopIncome;
    }








}
