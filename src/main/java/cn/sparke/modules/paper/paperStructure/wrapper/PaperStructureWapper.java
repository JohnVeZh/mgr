package cn.sparke.modules.paper.paperStructure.wrapper;

import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;

import java.util.List;
import java.util.Map;

/**
 * Created by tyd on 2017-8-7.
 */
public class PaperStructureWapper extends BaseControllerWrapper {

    public PaperStructureWapper(Object obj) {
        super(obj);
    }

    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {
        wrapperList.add(new WrapperBean("contentType", "paper_content_type", "contentTypeName"));
    }

    @Override
    protected void wrapperContent(Map<String, Object> wrapperList) {

    }
}
