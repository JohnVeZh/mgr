package cn.sparke.modules.goods.teacher.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.teacher.entity.TeacherEntity;

import java.util.List;


/**
 * 教师Dao
 *
 * @author spark
 * @Date 2017-07-21 11:06:39
 */
public interface TeacherMapper extends BaseMapper<TeacherEntity>{

    List<TeacherEntity> quertTeachers();


}
