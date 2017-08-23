package cn.sparke.modules.system.dept.service;

import cn.sparke.modules.system.dept.entity.Dept;
import cn.sparke.modules.system.dept.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public void deleteDept(String deptId) {
        Dept dept = deptMapper.getById(deptId);
        Dept query = new Dept();
        query.setPids(dept.getId());
        List<Dept> subDepts = deptMapper.findList(query);
        for (Dept temp : subDepts) {
            deptMapper.deleteById(temp.getId());
        }
        deptMapper.deleteById(deptId);
    }
}
