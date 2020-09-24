package top.weidaboy;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //springboot启动
@EnableEurekaClient    //开启连接
@EnableDiscoveryClient //服务发现
@EnableCircuitBreaker  //开启熔断
public class ConfigDeptProvider_hystrix_8001 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigDeptProvider_hystrix_8001.class,args);
    }

    //增加一个servlet
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        //后缀是从初始化监控页面得到的
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}

