package cn.sparke.modules.section.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 学段Entity
 *
 * @author spark
 * @Date 2017-07-19 16:33:08
 */
public class SectionEntity extends BaseEntity{
    //学段名称
    private String name;
    //学段code (1 2 3 4 5) 
    private Integer code;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCode(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return this.code;
    }

}
