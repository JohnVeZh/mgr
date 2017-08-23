package cn.sparke.modules.system.log.wrapper;

import cn.sparke.core.common.controller.BaseControllerWrapper;
import cn.sparke.core.common.entity.WrapperBean;
import cn.sparke.core.common.utils.Contrast;
import cn.sparke.core.common.utils.ToolUtil;

import java.util.List;
import java.util.Map;

/**
 * 日志列表的包装类
 *
 * @author spark
 * @date 2017年4月5日22:56:24
 */
public class LogWrapper extends BaseControllerWrapper {

    public LogWrapper(Object list) {
        super(list);
    }

    @Override
    protected void wrapperDict(List<WrapperBean> wrapperList) {

    }

    @Override
    protected void wrapperContent(Map<String, Object> model) {
        String message = (String) model.get("message");
        //如果信息过长,则只截取前100位字符串
        if (ToolUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            model.put("message", subMessage);
        }
        //如果信息中包含分割符号;;;   则分割字符串返给前台
        if (ToolUtil.isNotEmpty(message) && message.contains(Contrast.separator)) {
            String[] msgs = message.split(Contrast.separator);
            model.put("regularMessage", msgs);
        } else {
            model.put("regularMessage", message);
        }
    }


}
