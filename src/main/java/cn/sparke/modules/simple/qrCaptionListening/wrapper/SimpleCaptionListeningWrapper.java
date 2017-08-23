package cn.sparke.modules.simple.qrCaptionListening.wrapper;

import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/7/19
 */
public class SimpleCaptionListeningWrapper extends BaseControllerWrapper {
    public SimpleCaptionListeningWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {
        wrapperList.add(new WrapperBean("sectionCode","section","sectionCodeName"));
        wrapperList.add(new WrapperBean("useScene","qr_code_use_scene","useSceneName"));
        wrapperList.add(new WrapperBean("type","qr_code_type","typeName"));
        wrapperList.add(new WrapperBean("targetType","qr_code_target_type","targetTypeName"));
    }

    @Override
    protected void wrapperContent(Map<String, Object> wrapperList) {

    }
}
