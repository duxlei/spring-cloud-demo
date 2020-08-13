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
@EnableApolloConfig(
        order = 1,
        value = {
                "MD.application-temp-test",
                "MD.application-temp-public",
                "application"
        })
public class ShopBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBizApplication.class, args);
    }
}
