package cn.sparke.modules.system.user.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.system.user.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public interface UserMapper extends BaseMapper<User> {
    User getByAccount(String account);

    /**
     * 修改用户状态
     *
     * @param user
     * @date 2017年2月12日 下午8:42:31
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 设置用户的角色
     *
     * @return
     * @date 2017年2月13日 下午7:31:30
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

}
