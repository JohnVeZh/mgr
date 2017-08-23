package cn.sparke.modules.gift.planNetworkCourse.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.gift.planNetworkCourse.entity.PlanNetworkCourseEntity;
import com.github.pagehelper.Page;

/**
 * 学习方案绑定网课列表Dao
 *
 * @author spark
 * @Date 2017-08-21 14:57:58
 */
public interface PlanNetworkCourseMapper extends BaseMapper<PlanNetworkCourseEntity>{

    Page<PlanNetworkCourseEntity> queryList(PlanNetworkCourseEntity planNetworkCourseEntity);
}
