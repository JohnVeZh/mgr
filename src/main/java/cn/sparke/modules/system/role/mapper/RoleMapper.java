package cn.sparke.modules.system.role.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.system.role.entity.Role;

import java.util.List;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<ZTreeNode> roleTreeList();

    void deleteRolesById(String roleId);

    List<ZTreeNode> roleTreeListByRoleId(String[] strArray);
}
