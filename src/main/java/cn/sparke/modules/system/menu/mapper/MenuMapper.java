package cn.sparke.modules.system.menu.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.system.menu.entity.Menu;
import cn.sparke.modules.system.menu.entity.MenuNode;

import java.util.List;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> getResUrlsByRoleId(String roleId);

    List<MenuNode> getMenusByRoleIds(List<String> roleList);

    List<ZTreeNode> menuTreeList();

    Menu getByCode(String code);

    void deleteRelationByMenu(String menuId);

    List<ZTreeNode> menuTreeListByMenuIds(List<String> menuIds);

    List<String> getMenuIdsByRoleId(String roleId);
}
