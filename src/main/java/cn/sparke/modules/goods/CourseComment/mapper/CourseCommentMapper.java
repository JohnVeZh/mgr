package cn.sparke.modules.goods.CourseComment.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.CourseComment.entity.CourseCommentEntity;


/**
 * 网课评论Dao
 *
 * @author spark
 * @Date 2017-08-02 13:35:13
 */
public interface CourseCommentMapper extends BaseMapper<CourseCommentEntity> {


    void updatePraiseNum(CourseCommentEntity entity);

    void updateCommentNum(String networkCourseId);

    Integer getManagerreplyNum(String commentId);

    CourseCommentEntity getEntityById(String id);

    void addCommentReplyNum(String id);

    String getContentIdByCommentId(String id);


}
