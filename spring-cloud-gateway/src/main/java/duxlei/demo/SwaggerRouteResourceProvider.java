/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo;

import cn.hutool.core.collection.CollectionUtil;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2020/7/25 22:48
 */
@Primary
@Component
public class SwaggerRouteResourceProvider implements SwaggerResourcesProvider {

    // gateway路由（使用构造器注入）
    private RouteLocator routeLocator;

    @Value("${spring.application.name}")
    private String applicationName;


    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private EurekaClient eurekaClient;

    @Autowired
    public SwaggerRouteResourceProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {


//        routeLocator.getRoutes().subscribe(r -> {
//            System.out.println(r.getId());
//            System.out.println(r.getUri());
//            System.out.println(r.getUri().getHost());
//            System.out.println(r.getMetadata());
//        });

        List<Application> applicationList = eurekaClient.getApplications().getRegisteredApplications();
        List<SwaggerResource> swaggerResourceList = new ArrayList<>();
        applicationList.stream().filter(e -> !e.getName().equalsIgnoreCase(applicationName)).forEach(app -> {
            String appName = app.getName();
            List<InstanceInfo> instances = app.getInstances();
            if (CollectionUtil.isNotEmpty(instances)) {
                String swaggerName = instances.get(0).getMetadata().get("swagger-name");
                swaggerName = swaggerName == null ? appName : swaggerName;
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setName(swaggerName.toLowerCase());
                swaggerResource.setUrl("/" + appName.toLowerCase() + "/v3/api-docs");
                swaggerResource.setSwaggerVersion(DocumentationType.OAS_30.getVersion());
                swaggerResourceList.add(swaggerResource);
            }
        });
        return swaggerResourceList;

//        List<String> serviceList = discoveryClient.getServices();
//        return serviceList.stream().filter(e -> !e.equalsIgnoreCase(applicationName)).map(service -> {
//            SwaggerResource swaggerResource = new SwaggerResource();
//            swaggerResource.setName(service);
//            swaggerResource.setUrl("/" + service + "/v3/api-docs");
//            swaggerResource.setSwaggerVersion(DocumentationType.OAS_30.getVersion());
//            return swaggerResource;
//        }).collect(Collectors.toList());

    }


}
