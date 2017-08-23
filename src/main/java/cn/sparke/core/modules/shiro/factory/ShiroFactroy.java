package cn.sparke.core.modules.shiro.factory;

import cn.sparke.core.common.constants.factory.ConstantFactory;
import cn.sparke.core.common.utils.Convert;
import cn.sparke.core.common.utils.SpringContextHolder;
import cn.sparke.core.modules.shiro.ShiroUser;
import cn.sparke.core.modules.shiro.constants.ManagerStatus;
import cn.sparke.modules.system.menu.mapper.MenuMapper;
import cn.sparke.modules.system.user.entity.User;
import cn.sparke.modules.system.user.mapper.UserMapper;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String account) {

        User user = userMapper.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());            // 账号id
        shiroUser.setAccount(user.getAccount());// 账号
        shiroUser.setDeptId(user.getDeptid());    // 部门id
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));// 部门名称
        shiroUser.setName(user.getName());        // 用户名称

        String[] roleArray = Convert.toStrArray(user.getRoleid());// 角色集合
        List<String> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<String>();
        for (String roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(String roleId) {
        return menuMapper.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(String roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
