package cn.sparke.modules.business.message.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.business.message.mapper.MessageUserMapper;
import cn.sparke.modules.business.message.entity.MessageUserEntity;

/**
 * 消息推送Dao
 *
 * @author spark
 * @Date 2017-07-24 14:20:12
 */
@Service
public class MessageUserService{
    @Autowired
    private MessageUserMapper messageUserMapper;

    public void save(MessageUserEntity messageUser){
        messageUser.preInsert();
        messageUserMapper.insert(messageUser);
    }

    public void update(MessageUserEntity messageUser){
        messageUser.preUpdate();
        messageUserMapper.update(messageUser);
    }

    public MessageUserEntity get(MessageUserEntity messageUser){
        return messageUserMapper.get(messageUser);
    }

    public MessageUserEntity getById(String id){
        return messageUserMapper.getById(id);
    }
    public Page<MessageUserEntity> findList(MessageUserEntity messageUser){
       return messageUserMapper.findList(messageUser);
    }

    public void deleteById(String id){
      messageUserMapper.deleteById(id);
    }

}
