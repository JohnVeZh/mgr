package cn.sparke.modules.goods.bookType.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 图书类型Entity
 *
 * @author spark
 * @Date 2017-07-19 16:14:23
 */
public class BookTypeEntity extends BaseEntity{
    //类型名称
    private String name;
    //父类型ID
    private String parentId;


    //非数据库字段
    private String parentName;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    public String getParentId(){
        return this.parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
