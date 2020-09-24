package top.weidaboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.weidaboy.springcloud.pojo.Dept;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    RestTemplate restTemplate;

    //设置获取服务前缀
    //private static final String DEPT_URL_PREFIX = "http://localhost:8001";
    //地址应该是一个变量，通过服务名来访问，不应该写死
    private static final String DEPT_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    //Restful消费风格
    @RequestMapping("consumer/dept/add")
    public boolean addDept(Dept dept){
        //这里是postForObject！
        System.out.println(dept.getDname());
        return restTemplate.postForObject(DEPT_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }


    //post 比 get 多一个参数 get直接写在url ，post 要将参数或者实体类单独做一个参数
    @RequestMapping("consumer/dept/get/{id}")
    public Dept getDept(@PathVariable("id") Long id){
        return restTemplate
                .getForObject(DEPT_URL_PREFIX+"/dept/get/"+id, Dept.class);
    }

    @RequestMapping("consumer/dept/list")
    public List<Dept> getAllDept(){
        return restTemplate.getForObject(DEPT_URL_PREFIX+"/dept/list", List.class);
    }





}
