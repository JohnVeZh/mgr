package cn.sparke.core.common.constants.dictmap;


import cn.sparke.core.common.constants.dictmap.base.AbstractDictMap;

/**
 * 日志的字典
 *
 * @author spark
 * @date 2017-05-06 15:01
 */
public class LogDict extends AbstractDictMap {

    @Override
    public void init() {
        put("tips","备注");
    }

    @Override
    protected void initBeWrapped() {

    }
}
