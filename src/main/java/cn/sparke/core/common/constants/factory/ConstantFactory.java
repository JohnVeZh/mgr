package cn.sparke.core.common.constants.factory;

import cn.sparke.core.common.support.StrKit;
import cn.sparke.core.common.utils.Convert;
import cn.sparke.core.common.utils.SpringContextHolder;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.modules.system.dept.entity.Dept;
import cn.sparke.modules.system.dept.mapper.DeptMapper;
import cn.sparke.modules.system.menu.entity.Menu;
import cn.sparke.modules.system.menu.mapper.MenuMapper;
import cn.sparke.modules.system.role.entity.Role;
import cn.sparke.modules.system.role.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 常量的生产工厂
 *
 * @author spark
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private MenuMapper menuMapper;

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }


    /**
     * 通过角色ids获取角色名称
     */
    @Override
    public String getRoleName(String roleIds) {
        String[] roles = Convert.toStrArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (String role : roles) {
            Role roleObj = roleMapper.getById(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    public String getSingleRoleName(String roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            return "--";
        }
        Role roleObj = roleMapper.getById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    public String getSingleRoleTip(String roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            return "--";
        }
        Role roleObj = roleMapper.getById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }

    /**
     * 获取部门名称
     */
    @Override
    public String getDeptName(String deptId) {
        Dept dept = deptMapper.getById(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
            return dept.getFullname();
        }
        return "";
    }


    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(String menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuMapper.getById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

}
