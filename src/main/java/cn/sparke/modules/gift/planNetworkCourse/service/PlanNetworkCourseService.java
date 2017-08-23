package cn.sparke.modules.gift.planNetworkCourse.service;

import cn.sparke.modules.gift.planNetworkCourse.entity.PlanNetworkCourseEntity;
import cn.sparke.modules.gift.planNetworkCourse.mapper.PlanNetworkCourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 学习方案绑定网课列表Dao
 *
 * @author spark
 * @Date 2017-08-21 14:57:58
 */
@Service
public class PlanNetworkCourseService{
    @Autowired
    private PlanNetworkCourseMapper planNetworkCourseMapper;

    public void save(PlanNetworkCourseEntity planNetworkCourse){
        planNetworkCourse.preInsert();
        planNetworkCourseMapper.insert(planNetworkCourse);
    }

    public void update(PlanNetworkCourseEntity planNetworkCourse){
        planNetworkCourse.preUpdate();
        planNetworkCourseMapper.update(planNetworkCourse);
    }

    public PlanNetworkCourseEntity get(PlanNetworkCourseEntity planNetworkCourse){
        return planNetworkCourseMapper.get(planNetworkCourse);
    }

    public PlanNetworkCourseEntity getById(String id){
        return planNetworkCourseMapper.getById(id);
    }

    public Page<PlanNetworkCourseEntity> findList(PlanNetworkCourseEntity planNetworkCourse){
       return planNetworkCourseMapper.findList(planNetworkCourse);
    }

    public void deleteById(String id){
      planNetworkCourseMapper.deleteById(id);
    }

    public Page<PlanNetworkCourseEntity> queryList(PlanNetworkCourseEntity planNetworkCourseEntity) {
        return planNetworkCourseMapper.queryList(planNetworkCourseEntity);
    }
}
