package top.weidaboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Zuul9527 {
        public static void main(String[] args) {
            SpringApplication.run(Zuul9527.class,args);
        }
}
