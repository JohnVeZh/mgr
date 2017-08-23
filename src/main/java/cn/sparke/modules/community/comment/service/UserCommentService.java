package cn.sparke.modules.community.comment.service;

import cn.sparke.modules.community.comment.entity.po.UserCommentEntity;
import cn.sparke.modules.community.comment.entity.vo.UserCommentVoEntity;
import cn.sparke.modules.community.comment.mapper.UserCommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 活动评论Dao
 *
 * @author spark
 * @Date 2017-07-21 10:18:26
 */
@Service
public class UserCommentService {
    @Autowired
    private UserCommentMapper userCommentMapper;

    public void save(UserCommentEntity activityComment){
        activityComment.preInsert();
        userCommentMapper.insert(activityComment);
    }

    public void update(UserCommentEntity activityComment){
        activityComment.preUpdate();
        userCommentMapper.update(activityComment);
    }

    public UserCommentEntity get(UserCommentEntity activityComment){
        return userCommentMapper.get(activityComment);
    }

    public UserCommentEntity getById(String id){
        return userCommentMapper.getById(id);
    }
    public Page<UserCommentMapper> findList(UserCommentEntity activityComment){
       return userCommentMapper.findList(activityComment);
    }

    public void deleteById(String id){
      userCommentMapper.deleteById(id);
    }

    public Page<UserCommentVoEntity> findVoList(UserCommentEntity entity) {
        return userCommentMapper.findVoList(entity);
    }

    public UserCommentVoEntity getVoById(String activityCommentId) {
        return userCommentMapper.getVoById(activityCommentId);
    }
}
