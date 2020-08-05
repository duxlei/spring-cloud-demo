/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 自定义兼容spring cloud gateway服务接口try it out 404问题
 * @author Administrator
 * @date 2020/7/26 13:10
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
public class CustomWebMvcBasePathAndHostnameTransformationFilter implements WebMvcOpenApiTransformationFilter {

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();
        context.request().ifPresent(servletRequest -> {
            HttpRequest httpRequest = new ServletServerHttpRequest(servletRequest);;
            UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

            String scheme = uriComponents.getScheme();
            int port = uriComponents.getPort();
            boolean secure = "https".equals(scheme);
            String host = uriComponents.getHost();
            port = (port == -1 ? (secure ? 443 : 80) : port);
            String baseUrl = scheme + "://" + host + ":" + port;

            Enumeration<String> headerNames = servletRequest.getHeaderNames();
            // 服务地址
            String servicePath = null;
            while (headerNames.hasMoreElements()) {
                String head = headerNames.nextElement();
//                System.out.println(head + " : " + servletRequest.getHeader(head));
                if ("X-Forwarded-Prefix".equalsIgnoreCase(head)) {
                    servicePath = servletRequest.getHeader(head);
                }
            }
            if (servicePath != null) {
                while (servicePath.endsWith("/")) {
                    servicePath = servicePath.substring(0, servicePath.length() - 1);
                }
            }
            // 自定义兼容spring cloud gateway服务接口try it out 404问题
            String url = baseUrl + ( servicePath != null ? servicePath : "" );
            Server server = new Server();
            server.setUrl(url);
            server.setDescription("");

            openApi.servers(Collections.singletonList(server));
        });

        return openApi;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter == DocumentationType.OAS_30;
    }
}
