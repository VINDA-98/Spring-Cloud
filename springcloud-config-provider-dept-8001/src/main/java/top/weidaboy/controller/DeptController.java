package top.weidaboy.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import top.weidaboy.service.IDeptService;
import top.weidaboy.springcloud.pojo.Dept;

import java.util.List;

//提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping ("/dept/get/{id}")
    //设置备选方案
    @HystrixCommand(fallbackMethod = "hystrix_QueryById")
    public Dept queryById(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        //空值提醒
        if(dept == null){
            throw new RuntimeException("id:"+id+"的用户不存在,或者当前读取信息有误");
        }
        return dept;
    }

    //备选方案,Hystrix,当相同路径请求的服务发生错误异常等信息
    public Dept hystrix_QueryById(@PathVariable("id") Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id-->"+id+"+不存在该用户信息,null---->@Hystrix")
                .setDb_source("  获取不到对应该数据库信息,null---->@Hystrix");
    }


    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    @Autowired
    DiscoveryClient discoveryClient;

    //注册进来的微服务~，获取-些消息
    @GetMapping("/eureka/list")
    public Object discovery() {
        //获取微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>services: " + services);

        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        //得到某个具体微服务的信息,通过具体微服务的ID,applicationName
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+'\t'+
                            instance.getPort()+'\t'+
                            instance.getUri()+'\t'+
                            instance.getInstanceId()+'\t');
        }

        return this.discoveryClient;
    }

    //注册进来的微服务~，获取-些消息
    @GetMapping("/eureka/list/{serviceID}")
    public Object discoveryId(@PathVariable("serviceID") String serviceID) {
        //获取微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>services: " + services);

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceID);

        //得到某个具体微服务的信息,通过具体微服务的ID,applicationName
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+'\t'+
                            instance.getPort()+'\t'+
                            instance.getUri()+'\t'+
                            instance.getInstanceId()+'\t');
        }
        return this.discoveryClient;
    }
}
