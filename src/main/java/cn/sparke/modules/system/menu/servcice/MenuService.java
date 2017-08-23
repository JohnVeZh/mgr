package cn.sparke.modules.system.menu.servcice;

import cn.sparke.modules.system.menu.entity.Menu;
import cn.sparke.modules.system.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单服务
 *
 * @author spark
 * @date 2017-05-05 22:20
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public void delMenu(String menuId) {

        //删除菜单
        this.menuMapper.deleteById(menuId);

        //删除关联的relation
        this.menuMapper.deleteRelationByMenu(menuId);
    }

    public void delMenuContainSubMenus(String menuId) {
        Menu menu = menuMapper.getById(menuId);
        //删除当前菜单
        delMenu(menuId);
        //删除所有子菜单
        Menu queryMenu = new Menu();
        queryMenu.setPcodes(menu.getCode());
        List<Menu> menus = menuMapper.findList(queryMenu);
        for (Menu temp : menus) {
            delMenu(temp.getId());
        }
    }
}
