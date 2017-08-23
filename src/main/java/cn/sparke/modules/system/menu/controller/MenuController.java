package cn.sparke.modules.system.menu.controller;

import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.constants.dictmap.MenuDict;
import cn.sparke.core.common.constants.factory.ConstantFactory;
import cn.sparke.core.common.constants.tips.Tip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.menu.constants.MenuStatus;
import cn.sparke.modules.system.menu.entity.Menu;
import cn.sparke.modules.system.menu.mapper.MenuMapper;
import cn.sparke.modules.system.menu.servcice.MenuService;
import cn.sparke.modules.system.menu.wrapper.MenuWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author spark
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    private static String PREFIX = "/system/menu/";


    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuService menuService;

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "menu.html";
    }

    /**
     * 查看菜单
     */
    @RequestMapping(value = "/view/{menuId}")
    @ResponseBody
    public Tip view(@PathVariable String menuId) {
        this.menuMapper.getById(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Menu menu) {
        List<Menu> menus = this.menuMapper.findList(menu);
        return super.warpObject(new MenuWrapper(menus));
    }

    /**
     * 新增页面
     */
    @RequestMapping(value = "/menu_add")
    public String menuAdd() {
        return PREFIX + "menu_add.html";
    }

    /**
     * 新增菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/add")
    @BussinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
    @ResponseBody
    public Tip add(@Valid Menu menu) {
        //判断是否存在该编号
        Menu existMenu = this.menuMapper.getByCode(menu.getCode());
        if (ToolUtil.isNotEmpty(existMenu)) {
            throw new BussinessException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //设置父级菜单编号
        menuSetPcode(menu);
        menu.setStatus(MenuStatus.ENABLE.getCode());
        menu.preInsert();
        this.menuMapper.insert(menu);
        return SUCCESS_TIP;
    }

    /**
     * 编辑页面
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/menu_edit/{menuId}")
    public String menuEdit(@PathVariable String menuId, Model model) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuMapper.getById(menuId);
        if (StringUtils.isEmpty(menu.getpId())) {
            menu.setpId("0");
        }
        model.addAttribute("menu", menu);
        LogObjectHolder.me().set(menu);
        return PREFIX + "menu_edit.html";
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuMapper.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 修改菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改菜单", key = "name", dict = MenuDict.class)
    @ResponseBody
    public Tip edit(@Valid Menu menu) {
        //设置父级菜单编号
        menuSetPcode(menu);
        menu.preUpdate();
        this.menuMapper.update(menu);
        return SUCCESS_TIP;
    }


    /**
     * 删除菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除菜单", key = "menuId", dict = MenuDict.class)
    @ResponseBody
    public Tip remove(@RequestParam String menuId) {
        //缓存菜单的名称
        LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));
        this.menuService.delMenuContainSubMenus(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 根据请求的父级菜单编号设置pcode和层级
     */
    private void menuSetPcode(@Valid Menu menu) {
        if (ToolUtil.isEmpty(menu.getpId()) || menu.getpId().equals("0")) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevel(1);
        } else {
            //这里传递的pcode是parentid
            Menu pMenu = menuMapper.getById(menu.getpId());
            Integer pLevels = pMenu.getLevel();
            menu.setPcode(pMenu.getCode());

            //如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) {
                throw new BussinessException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            menu.setLevel(pLevels + 1);
            menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/menuTreeListByRoleId/{roleId}")
    @ResponseBody
    public List<ZTreeNode> menuTreeListByRoleId(@PathVariable String roleId) {
        List<String> menuIds = this.menuMapper.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> roleTreeList = this.menuMapper.menuTreeList();
            return roleTreeList;
        } else {
            List<ZTreeNode> roleTreeListByUserId = this.menuMapper.menuTreeListByMenuIds(menuIds);
            return roleTreeListByUserId;
        }
    }
}
