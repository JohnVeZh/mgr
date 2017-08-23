package cn.sparke.core.common.constants.dictmap;


import cn.sparke.core.common.constants.dictmap.base.AbstractDictMap;

/**
 * 字典map
 *
 * @author spark
 * @date 2017-05-06 15:43
 */
public class DictMap extends AbstractDictMap {

    @Override
    public void init() {
        put("label", "标签");
        put("type", "类型");
        put("description", "描述");
        put("value", "值");
    }

    @Override
    protected void initBeWrapped() {

    }
}
