package cn.sparke.modules.paper.specialExplain.wrapper;

import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/7/19
 */
public class SpecialExplainWrapper extends BaseControllerWrapper {
    public SpecialExplainWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {
        wrapperList.add(new WrapperBean("type","special_type","typeName"));
        wrapperList.add(new WrapperBean("sectionCode","section","sectionCodeName"));
        wrapperList.add(new WrapperBean("contentType","explain_content_type","contentTypeName"));
    }

    @Override
    protected void wrapperContent(Map<String, Object> wrapperList) {

    }
}
