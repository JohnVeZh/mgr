package cn.sparke.modules.gift.recommendCourse.service;

import cn.sparke.modules.gift.recommendCourse.entity.GiftRecommendCourseEntity;
import cn.sparke.modules.gift.recommendCourse.mapper.GiftRecommendCourseMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程推荐Dao
 *
 * @author spark
 * @Date 2017-08-22 09:48:17
 */
@Service
public class GiftRecommendCourseService{
    @Autowired
    private GiftRecommendCourseMapper giftRecommendCourseMapper;

    public void save(GiftRecommendCourseEntity giftRecommendCourse){
        giftRecommendCourse.preInsert();
        giftRecommendCourseMapper.insert(giftRecommendCourse);
    }

    public void update(GiftRecommendCourseEntity giftRecommendCourse){
        giftRecommendCourse.preUpdate();
        giftRecommendCourseMapper.update(giftRecommendCourse);
    }

    public GiftRecommendCourseEntity get(GiftRecommendCourseEntity giftRecommendCourse){
        return giftRecommendCourseMapper.get(giftRecommendCourse);
    }

    public GiftRecommendCourseEntity getById(String id){
        return giftRecommendCourseMapper.getById(id);
    }

    public Page<GiftRecommendCourseEntity> findList(GiftRecommendCourseEntity giftRecommendCourse){
       return giftRecommendCourseMapper.findList(giftRecommendCourse);
    }

    public void deleteById(String id){
      giftRecommendCourseMapper.deleteById(id);
    }

}
