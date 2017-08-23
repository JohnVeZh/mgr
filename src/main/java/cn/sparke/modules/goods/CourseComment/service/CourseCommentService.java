package cn.sparke.modules.goods.CourseComment.service;

import cn.sparke.modules.goods.CourseComment.entity.CourseCommentEntity;
import cn.sparke.modules.goods.CourseComment.mapper.CourseCommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

import java.util.List;


/**
 * 网课评论Dao
 *
 * @author spark
 * @Date 2017-08-02 13:35:13
 */
@Service
public class CourseCommentService{
    @Autowired
    private CourseCommentMapper courseCommentMapper;

    public void save(CourseCommentEntity courseComment){
        courseComment.preInsert();
        courseCommentMapper.insert(courseComment);
    }

    public void update(CourseCommentEntity courseComment){
        courseComment.preUpdate();
        courseCommentMapper.update(courseComment);
    }

    public CourseCommentEntity get(CourseCommentEntity courseComment){
        return courseCommentMapper.get(courseComment);
    }

    public CourseCommentEntity getById(String id){
        return courseCommentMapper.getById(id);
    }
    public Page<CourseCommentEntity> findList(CourseCommentEntity courseComment){
        Page<CourseCommentEntity> list =courseCommentMapper.findList(courseComment);

        for (CourseCommentEntity entity:list) {
           Integer count = courseCommentMapper.getManagerreplyNum(entity.getId());
           entity.setCount(count);
        }
        return list;
    }

    public void deleteById(String id){
        CourseCommentEntity entity = courseCommentMapper.getById(id);
        Integer flag = courseCommentMapper.deleteById(id);
        courseCommentMapper.updateCommentNum(entity.getContentId());
    }

    public void updatePraiseNum(CourseCommentEntity entity){
      courseCommentMapper.updatePraiseNum(entity);
    }

}
