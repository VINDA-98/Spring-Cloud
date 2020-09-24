package top.weidaboy.controller;

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
    public Dept queryById(@PathVariable("id") Long id){
        return deptService.queryById(id);
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
