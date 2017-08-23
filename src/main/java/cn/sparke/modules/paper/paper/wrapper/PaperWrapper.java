package cn.sparke.modules.paper.paper.wrapper;

import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/7/19
 */
public class PaperWrapper extends BaseControllerWrapper {
    public PaperWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {
        wrapperList.add(new WrapperBean("contentType","paper_content_type","contentTypeName"));
    }

    @Override
    protected void wrapperContent(Map<String, Object> wrapperList) {

    }
}
