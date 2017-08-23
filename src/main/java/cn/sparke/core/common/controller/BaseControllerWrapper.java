package cn.sparke.core.common.controller;

import cn.sparke.core.common.entity.WrapperBean;
import cn.sparke.core.common.support.BeanKit;
import cn.sparke.modules.system.dict.entity.DictEntity;
import cn.sparke.modules.system.dict.utils.DictUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 控制器查询结果的包装类基类
 *
 * @author spark
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseControllerWrapper {

    public Object obj = null;
    private List<WrapperBean> wrapperList = new ArrayList<>();

    public BaseControllerWrapper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public Object warp() {
        if (this.obj == null) {
            return null;
        }

        wrapperDict(wrapperList);
        if (wrapperList.size() > 0) {
            wrapperList = wrapperList.stream().map(wrapperBean -> {
                wrapperBean.setDictList(DictUtils.getDictList(wrapperBean.getDictType()));
                return wrapperBean;
            }).collect(Collectors.toList());
        }
        if (this.obj instanceof List) {
            List<Object> list = (List<Object>) this.obj;
            List<Map> resultList = new ArrayList<>();
            for (Object bean : list) {
                Map map = BeanKit.beanToMap(bean);
                wrapperContent(map);
                process(map);
                resultList.add(map);
            }
            return resultList;
        } else if (this.obj instanceof Map) {
            Map<String, Object> model = (Map<String, Object>) this.obj;
            wrapperContent(model);
            process(model);
            return model;
        } else {
            Map<String, Object> model = BeanKit.beanToMap(this.obj);
            wrapperContent(model);
            process(model);
            return model;
        }
    }

    private void process(Map map) {
        if (wrapperList.size() == 0) {
            return;
        }
        wrapperList.forEach(wrapperBean -> {
            Object value = map.get(wrapperBean.getFiledName());
            if (value != null) {
                for (DictEntity dictEntity : wrapperBean.getDictList()) {
                    if (dictEntity.getValue().equals(value + "")) {
                        map.put(wrapperBean.getShowName(), dictEntity.getLabel());
                    }
                }
            }
        });
    }

    protected abstract void wrapperDict(List<WrapperBean> wrapperList);

    protected abstract void wrapperContent(Map<String, Object> wrapperList);
}
