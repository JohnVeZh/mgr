package cn.sparke.modules.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.user.mapper.UserAddressMapper;
import cn.sparke.modules.user.entity.UserAddressEntity;

/**
 * 收货地址Dao
 *
 * @author spark
 * @Date 2017-07-22 10:49:11
 */
@Service
public class UserAddressService{
    @Autowired
    private UserAddressMapper userAddressMapper;

    public void save(UserAddressEntity userAddress){
        userAddress.preInsert();
        userAddressMapper.insert(userAddress);
    }

    public void update(UserAddressEntity userAddress){
        userAddress.preUpdate();
        userAddressMapper.update(userAddress);
    }

    public UserAddressEntity get(UserAddressEntity userAddress){
        return userAddressMapper.get(userAddress);
    }

    public UserAddressEntity getById(String id){
        return userAddressMapper.getById(id);
    }
    public Page<UserAddressEntity> findList(UserAddressEntity userAddress){
       return userAddressMapper.findList(userAddress);
    }

    public void deleteById(String id){
      userAddressMapper.deleteById(id);
    }

}
