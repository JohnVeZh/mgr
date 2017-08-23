package cn.sparke.modules.system.role.service;

import cn.sparke.core.common.utils.Convert;
import cn.sparke.modules.system.role.entity.Relation;
import cn.sparke.modules.system.role.mapper.RelationMapper;
import cn.sparke.modules.system.role.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RelationMapper relationMapper;

    public void setAuthority(String roleId, String ids) {

        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);

        // 添加新的权限
        for (String id : Convert.toStrArray(ids)) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            relation.preInsert();
            this.relationMapper.insert(relation);
        }
    }

    public void delRoleById(String roleId) {
        //删除角色
        this.roleMapper.deleteById(roleId);

        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);
    }

}
