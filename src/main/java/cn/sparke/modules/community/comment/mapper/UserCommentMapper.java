package cn.sparke.modules.community.comment.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.community.comment.entity.po.UserCommentEntity;
import cn.sparke.modules.community.comment.entity.vo.UserCommentVoEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 活动评论Dao
 *
 * @author spark
 * @Date 2017-07-21 10:18:26
 */
public interface UserCommentMapper extends BaseMapper<UserCommentEntity>{

    Page<UserCommentVoEntity> findVoList(UserCommentEntity entity);

    UserCommentVoEntity getVoById(@Param("id") String id);
}
