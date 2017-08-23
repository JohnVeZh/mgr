package cn.sparke.modules.community.activity.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.community.activity.entity.ActivityEntity;
import com.github.pagehelper.Page;

/**
 * 活动Dao
 *
 * @author spark
 * @Date 2017-07-19 09:57:36
 */
public interface ActivityMapper extends BaseMapper<ActivityEntity>{


    Page findListWithoutContent(ActivityEntity entity);
}
