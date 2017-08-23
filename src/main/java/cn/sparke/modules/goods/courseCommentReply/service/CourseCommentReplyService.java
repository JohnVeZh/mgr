package cn.sparke.modules.goods.courseCommentReply.service;

import cn.sparke.modules.business.message.entity.MessageEntity;
import cn.sparke.modules.business.message.entity.MessageUserEntity;
import cn.sparke.modules.business.message.mapper.MessageMapper;
import cn.sparke.modules.business.message.mapper.MessageUserMapper;
import cn.sparke.modules.goods.CourseComment.mapper.CourseCommentMapper;
import cn.sparke.modules.goods.courseCommentReply.entity.CourseCommentReplyEntity;
import cn.sparke.modules.goods.courseCommentReply.mapper.CourseCommentReplyMapper;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 评论Dao
 *
 * @author spark
 * @Date 2017-08-18 10:16:26
 */
@Service
public class CourseCommentReplyService{
    @Autowired
    private CourseCommentReplyMapper courseCommentReplyMapper;
    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageUserMapper messageUserMapper;

    public void save(CourseCommentReplyEntity courseCommentReply){
        //根据评论id获取活动id
        String activityId = courseCommentMapper.getContentIdByCommentId(courseCommentReply.getCommentId());
        if (activityId == null) {
            return;
        }
        courseCommentReply.preInsert();
        courseCommentReplyMapper.insert(courseCommentReply);
        //增加评论回复数量
        courseCommentMapper.addCommentReplyNum(courseCommentReply.getCommentId());
        //插入到消息中
        MessageEntity messageBean = new MessageEntity();
        messageBean.setTitle("您的评论收到新回复，点击查看");
        messageBean.setContentId(activityId);
        messageBean.setJumpType(6);//网课
        messageBean.setTargetType(1);//个人
        messageBean.setPushStatus(0);
        messageBean.preInsert();

        MessageUserEntity messageUserBean = new MessageUserEntity();
        messageUserBean.setUserId(courseCommentReply.getUserId());
        messageUserBean.setMsgId(messageBean.getId());
        messageUserBean.preInsert();

        messageMapper.insert(messageBean);
        messageUserMapper.insert(messageUserBean);
    }

    public void update(CourseCommentReplyEntity courseCommentReply){
        courseCommentReply.preUpdate();
        courseCommentReplyMapper.update(courseCommentReply);

        //根据评论id获取活动id
        String activityId = courseCommentMapper.getContentIdByCommentId(courseCommentReply.getCommentId());
        //插入到消息中
        MessageEntity messageBean = new MessageEntity();
        messageBean.setTitle("您的评论收到新回复，点击查看");
        messageBean.setContentId(activityId);
        messageBean.setJumpType(6);//网课
        messageBean.setTargetType(1);//个人
        messageBean.setPushStatus(0);
        messageBean.preInsert();

        MessageUserEntity messageUserBean = new MessageUserEntity();
        messageUserBean.setUserId(courseCommentReply.getUserId());
        messageUserBean.setMsgId(messageBean.getId());
        messageUserBean.preInsert();

        messageMapper.insert(messageBean);
        messageUserMapper.insert(messageUserBean);
    }

    public CourseCommentReplyEntity get(CourseCommentReplyEntity courseCommentReply){
        return courseCommentReplyMapper.get(courseCommentReply);
    }

    public CourseCommentReplyEntity getById(String id){
        return courseCommentReplyMapper.getById(id);
    }
    public Page<CourseCommentReplyEntity> findList(CourseCommentReplyEntity courseCommentReply){
       return courseCommentReplyMapper.findList(courseCommentReply);
    }

    public void deleteById(String id){
      courseCommentReplyMapper.deleteById(id);
    }

    public CourseCommentReplyEntity getByCommentId(String commentId){
        return courseCommentReplyMapper.getByCommentId(commentId);
    }

}
