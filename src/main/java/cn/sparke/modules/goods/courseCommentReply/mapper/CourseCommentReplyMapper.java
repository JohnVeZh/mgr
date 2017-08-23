package cn.sparke.modules.goods.courseCommentReply.mapper;


import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.courseCommentReply.entity.CourseCommentReplyEntity;

/**
 * 评论Dao
 *
 * @author spark
 * @Date 2017-08-18 10:16:26
 */
public interface CourseCommentReplyMapper extends BaseMapper<CourseCommentReplyEntity> {

    CourseCommentReplyEntity getByCommentId(String commentId);
}
