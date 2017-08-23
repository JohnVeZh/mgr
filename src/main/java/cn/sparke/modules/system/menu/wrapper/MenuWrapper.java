package cn.sparke.modules.system.menu.wrapper;


import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;
import cn.sparke.modules.system.dict.utils.DictUtils;
import cn.sparke.modules.system.menu.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author spark
 * @date 2017年2月19日15:07:29
 */
public class MenuWrapper extends BaseControllerWrapper {

    public MenuWrapper(List<Menu> list) {
        super(list);
    }


    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {
        wrapperList.add(new WrapperBean("ismenu","menu_is_menu","isMenuName"));
        wrapperList.add(new WrapperBean("status","menu_is_status","statusName"));
    }

    @Override
    protected void wrapperContent(Map<String, Object> wrapperList) {

    }
}
