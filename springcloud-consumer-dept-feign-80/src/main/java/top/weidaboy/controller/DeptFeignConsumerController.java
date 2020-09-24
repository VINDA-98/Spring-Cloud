package top.weidaboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.weidaboy.springcloud.pojo.Dept;
import top.weidaboy.springcloud.service.IDeptClientService;

import java.util.List;

@RestController
public class DeptFeignConsumerController {

    @Autowired
    //@Qualifier("SPRINGCLOUD-PROVIDER-DEPT")
    //注解爆红可以将service模块移植到feign模块中,并不影响实际操作
    private IDeptClientService deptClientService = null;

    //Restful消费风格
    @RequestMapping("/consumer/dept/add")
    public boolean addDept(Dept dept){
      return this.deptClientService.addDept(dept);
    }

    //post 比 get 多一个参数 get直接写在url ，post 要将参数或者实体类单独做一个参数
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept getDept(@PathVariable("id") Long id){
        return this.deptClientService.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> getAllDept(){
        return this.deptClientService.queryAll();
    }
}
