package top.weidaboy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.weidaboy.springcloud.pojo.Dept;

import java.util.List;

@Mapper
@Repository
public interface IDeptDao {

    boolean addDept(Dept dept);

    Dept queryById(Long id);

    List<Dept> queryAll();
}
