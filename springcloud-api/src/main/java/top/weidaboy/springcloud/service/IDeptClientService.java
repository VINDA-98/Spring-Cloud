package top.weidaboy.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.weidaboy.springcloud.pojo.Dept;

import java.util.List;

@Component
@FeignClient(value="SPRINGCLOUD-CONFIG-PROVIDER-DEPT",fallbackFactory = IDeptClientServiceFailBackFactory.class)
public interface IDeptClientService {

    @PostMapping("/dept/add")
    boolean addDept(Dept dept);

    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();
}
