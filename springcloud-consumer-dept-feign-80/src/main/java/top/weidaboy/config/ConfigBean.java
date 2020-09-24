package top.weidaboy.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//Configuration相当于  applicationContext.xml
public class ConfigBean {
    //添加负载均衡实现 RestTemplate，RestTemplate负责发起消费服务，就对该请求做出处理
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
