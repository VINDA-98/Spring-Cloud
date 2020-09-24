package top.weidaboy.service;
import top.weidaboy.springcloud.pojo.Dept;
import java.util.List;

public interface IDeptService {
    boolean addDept(Dept dept);

    Dept queryById(Long id);

    List<Dept> queryAll();
}
