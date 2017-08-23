package cn.sparke.modules.community.userCommentReply.service;

import cn.sparke.modules.community.userCommentReply.entity.UserCommentReplyEntity;
import cn.sparke.modules.community.userCommentReply.mapper.UserCommentReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 用户评论回复Dao
 *
 * @author spark
 * @Date 2017-07-22 11:27:00
 */
@Service
public class UserCommentReplyService{
    @Autowired
    private UserCommentReplyMapper userCommentReplyMapper;

    public void save(UserCommentReplyEntity userCommentReply){
        userCommentReply.preInsert();
        userCommentReplyMapper.insert(userCommentReply);
    }

    public void update(UserCommentReplyEntity userCommentReply){
        userCommentReply.preUpdate();
        userCommentReplyMapper.update(userCommentReply);
    }

    public UserCommentReplyEntity get(UserCommentReplyEntity userCommentReply){
        return userCommentReplyMapper.get(userCommentReply);
    }

    public UserCommentReplyEntity getById(String id){
        return userCommentReplyMapper.getById(id);
    }
    public Page<UserCommentReplyEntity> findList(UserCommentReplyEntity userCommentReply){
       return userCommentReplyMapper.findList(userCommentReply);
    }

    public void deleteById(String id){
      userCommentReplyMapper.deleteById(id);
    }

}
