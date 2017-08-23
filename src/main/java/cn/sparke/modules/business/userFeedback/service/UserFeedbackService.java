package cn.sparke.modules.business.userFeedback.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.business.userFeedback.mapper.UserFeedbackMapper;
import cn.sparke.modules.business.userFeedback.entity.UserFeedbackEntity;

/**
 * 用户反馈Dao
 *
 * @author spark
 * @Date 2017-07-25 14:43:51
 */
@Service
public class UserFeedbackService{
    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    public void save(UserFeedbackEntity userFeedback){
        userFeedback.preInsert();
        userFeedbackMapper.insert(userFeedback);
    }

    public void update(UserFeedbackEntity userFeedback){
        userFeedback.preUpdate();
        userFeedbackMapper.update(userFeedback);
    }

    public UserFeedbackEntity get(UserFeedbackEntity userFeedback){
        return userFeedbackMapper.get(userFeedback);
    }

    public UserFeedbackEntity getById(String id){
        return userFeedbackMapper.getById(id);
    }
    public Page<UserFeedbackEntity> findList(UserFeedbackEntity userFeedback){
       return userFeedbackMapper.findList(userFeedback);
    }

    public void deleteById(String id){
      userFeedbackMapper.deleteById(id);
    }

}
