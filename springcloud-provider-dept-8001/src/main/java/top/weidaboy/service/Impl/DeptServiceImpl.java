package top.weidaboy.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weidaboy.dao.IDeptDao;
import top.weidaboy.service.IDeptService;
import top.weidaboy.springcloud.pojo.Dept;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private IDeptDao deptDao;

    @Override
    public boolean addDept(Dept dept) {
        System.out.println(dept);
        return deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }



}
