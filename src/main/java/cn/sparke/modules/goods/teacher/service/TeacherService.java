package cn.sparke.modules.goods.teacher.service;

import cn.sparke.modules.goods.teacher.entity.TeacherEntity;
import cn.sparke.modules.goods.teacher.mapper.TeacherMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;


/**
 * 教师Dao
 *
 * @author spark
 * @Date 2017-07-21 11:06:39
 */
@Service
public class TeacherService{
    @Autowired
    private TeacherMapper teacherMapper;

    public void save(TeacherEntity teacher){
        teacher.preInsert();
        teacherMapper.insert(teacher);
    }

    public void update(TeacherEntity teacher){
        teacher.preUpdate();
        teacherMapper.update(teacher);
    }

    public TeacherEntity get(TeacherEntity teacher){
        return teacherMapper.get(teacher);
    }

    public TeacherEntity getById(String id){
        return teacherMapper.getById(id);
    }
    public Page<TeacherEntity> findList(TeacherEntity teacher){
       return teacherMapper.findList(teacher);
    }

    public void deleteById(String id){
      teacherMapper.deleteById(id);
    }

}
