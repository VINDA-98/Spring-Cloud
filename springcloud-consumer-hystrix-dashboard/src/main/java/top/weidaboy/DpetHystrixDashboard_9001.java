package top.weidaboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication //springboot启动
@EnableHystrixDashboard  //开启监控
public class DpetHystrixDashboard_9001 {
        public static void main(String[] args) {
            SpringApplication.run(DpetHystrixDashboard_9001.class,args);
        }
}
