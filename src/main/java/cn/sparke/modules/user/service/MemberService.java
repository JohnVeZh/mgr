package cn.sparke.modules.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.user.mapper.MemberMapper;
import cn.sparke.modules.user.entity.UserEntity;

/**
 * 用户Dao
 *
 * @author spark
 * @Date 2017-07-20 10:35:28
 */
@Service("Member")
public class MemberService {
    @Autowired
    private MemberMapper userMapper;

    public void save(UserEntity user){
        user.preInsert();
        userMapper.insert(user);
    }

    public void update(UserEntity user){
        user.preUpdate();
        userMapper.update(user);
    }

    public UserEntity get(UserEntity user){
        return userMapper.get(user);
    }

    public UserEntity getById(String id){
        return userMapper.getById(id);
    }
    public Page<UserEntity> findList(UserEntity user){
       return userMapper.findList(user);
    }

    public void deleteById(String id){
      userMapper.deleteById(id);
    }

}
