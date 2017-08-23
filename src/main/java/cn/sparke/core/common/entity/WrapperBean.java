package cn.sparke.core.common.entity;

import cn.sparke.modules.system.dict.entity.DictEntity;

import java.util.List;

/**
 * Created by zhangbowen on 2017/7/18.
 */
public class WrapperBean {
    private String filedName;
    private String dictType;
    private String showName;
    private List<DictEntity> dictList;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public WrapperBean(String filedName, String dictType, String showName) {
        this.filedName = filedName;
        this.dictType = dictType;
        this.showName = showName;
    }

    public List<DictEntity> getDictList() {
        return dictList;
    }

    public void setDictList(List<DictEntity> dictList) {
        this.dictList = dictList;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }
}
