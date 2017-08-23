package cn.sparke.modules.community.activity.service;

import cn.sparke.modules.community.activity.entity.ActivityEntity;
import cn.sparke.modules.community.activity.mapper.ActivityMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动Dao
 *
 * @author spark
 * @Date 2017-07-19 09:57:36
 */
@Service
public class IActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    public void save(ActivityEntity activity) {
        activity.preInsert();
        activityMapper.insert(activity);
    }

    public void update(ActivityEntity activity) {
        activity.preUpdate();
        activityMapper.update(activity);
    }

    public ActivityEntity get(ActivityEntity activity) {
        return activityMapper.get(activity);
    }

    public ActivityEntity getById(String id) {
        return activityMapper.getById(id);
    }

    public Page<ActivityEntity> findList(ActivityEntity activity) {
        return activityMapper.findList(activity);
    }

    public void deleteById(String id) {
        activityMapper.deleteById(id);
    }

    public Page findListWithoutContent(ActivityEntity entity) {
        return activityMapper.findListWithoutContent(entity);
    }
}
