package top.weidaboy.springcloud.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import top.weidaboy.springcloud.pojo.Dept;

import java.util.ArrayList;
import java.util.List;

//降级 刚刚我们是对一个服务熔断,现在是对一个类
@Component
public class IDeptClientServiceFailBackFactory implements FallbackFactory {

/*
    @Override  Object:就是我们要返回的服务对象,这里就是IDeptClientService,
                      需要重写IDeptClientService接口
    public Object create(Throwable throwable) {
        return null;
    }
*/
    @Override
    public IDeptClientService create(Throwable throwable) {
        return new IDeptClientService() {

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            public Dept queryById(Long id) {
                return new Dept().
                        setDeptno(id)
                        .setDname("id-->"+id+"+不存在该用户信息,客户端提供降级服务,关闭中...null---->@Hystrix")
                        .setDb_source("无数据,null---->@Hystrix");
            }

            @Override
            public List<Dept> queryAll() {
                List<Dept> depts = new ArrayList<>();
                depts.add(new Dept()
                        .setDname("+不存在该用户信息,客户端提供降级服务,关闭中...null---->@Hystrix")
                        .setDb_source("无数据,null---->@Hystrix"));
                return depts;
            }

        };
    }
}
