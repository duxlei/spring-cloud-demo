package duxlei.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableWebSecurity
public class UserBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBizApplication.class, args);
    }
}
