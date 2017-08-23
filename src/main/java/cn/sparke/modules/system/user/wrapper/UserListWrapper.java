package cn.sparke.modules.system.user.wrapper;


import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;
import cn.sparke.modules.system.user.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author spark
 * @date 2017年2月13日 下午10:47:03
 */
public class UserListWrapper extends BaseControllerWrapper {
    public UserListWrapper(List<User> list) {
        super(list);
    }

    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {
        wrapperList.add(new WrapperBean("sex","user_sex","sexName"));
        wrapperList.add(new WrapperBean("status","user_status","statusName"));
    }

    @Override
    protected void wrapperContent(Map<String, Object> wrapperList) {

    }
}
