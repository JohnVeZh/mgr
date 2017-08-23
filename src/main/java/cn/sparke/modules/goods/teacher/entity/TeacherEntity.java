package cn.sparke.modules.goods.teacher.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 教师Entity
 *
 * @author spark
 * @Date 2017-07-21 11:06:39
 */
public class TeacherEntity extends BaseEntity{
    //名字
    private String name;
    //手机
    private String moblie;
    //教师的标签
    private String tags;
    //邮箱
    private String email;
    //老师头像
    private String headImg;
    //性别，0男，1女
    private Integer gender;
    //简介
    private String introduce;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setMoblie(String moblie){
        this.moblie = moblie;
    }
    public String getMoblie(){
        return this.moblie;
    }
    public void setTags(String tags){
        this.tags = tags;
    }
    public String getTags(){
        return this.tags;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setHeadImg(String headImg){
        this.headImg = headImg;
    }
    public String getHeadImg(){
        return this.headImg;
    }
    public void setGender(Integer gender){
        this.gender = gender;
    }
    public Integer getGender(){
        return this.gender;
    }
    public void setIntroduce(String introduce){
        this.introduce = introduce;
    }
    public String getIntroduce(){
        return this.introduce;
    }

}
