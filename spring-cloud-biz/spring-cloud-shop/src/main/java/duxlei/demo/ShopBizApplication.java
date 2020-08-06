package duxlei.demo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig(value = {"MD.application-temp-test", "application"})
public class ShopBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBizApplication.class, args);
    }
}
