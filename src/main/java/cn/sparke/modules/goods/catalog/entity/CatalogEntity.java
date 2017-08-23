package cn.sparke.modules.goods.catalog.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 课程目录Entity
 *
 * @author spark
 * @Date 2017-07-24 18:29:41
 */
public class CatalogEntity extends BaseEntity{
    //网课ID
    private String networkCourseId;
    //目录名称
    private String name;

    public void setNetworkCourseId(String networkCourseId){
        this.networkCourseId = networkCourseId;
    }
    public String getNetworkCourseId(){
        return this.networkCourseId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

}
