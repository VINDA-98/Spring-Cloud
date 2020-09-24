package top.weidaboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"top.weidaboy.springcloud"}) //打开FeignClient连接
//@ComponentScan({"top.weidaboy.springcloud"})
public class ConfigDeptConsumer_feign {
    public static void main(String[] args) {
        SpringApplication.run(ConfigDeptConsumer_feign.class,args);
    }
}
