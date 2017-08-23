package cn.sparke.modules.user.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.core.common.support.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.math.*;
import java.util.Date;

/**
 * 用户Entity
 *
 * @author spark
 * @Date 2017-07-20 10:35:28
 */
public class UserEntity extends BaseEntity{
    //手机号
    private String phone;
    //密码
    private String password;
    //qq open id
    private String qqOpenId;
    //weixin_open_id
    private String weixinOpenId;
    //weibo open id
    private String weiboOpenId;
    //用户头像
    private String headerImg;
    //用户昵称
    private String nickname;
    //性别:0保密;1:男;2:女;
    private Integer sex;
    //真实姓名
    private String realName;
    //用户状态::0正常;1:禁用;
    private Integer userStatus;
    //学校名称
    private String schoolName;
    //入学时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrollYear;
    //用户生日
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    //注册渠道(0.老用户 1.android 2.iOS 3.M站 4.PC 5.知米6.管理员添加)
    private Integer regType;
    //推送开关(0.关 1.开)
    private Integer isPush;
    //最后登录时间
    private Date lastLoginDate;
    //学段(1.4级 2.6级 3.考研 4.英专 5.高中英语 6.初中英语 7.小学英语)
    private Integer sectionCode;
    //是否自动移除错题(0. 否 1.是)
    private Integer isAutoRemove;

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setQqOpenId(String qqOpenId){
        this.qqOpenId = qqOpenId;
    }
    public String getQqOpenId(){
        return this.qqOpenId;
    }
    public void setWeixinOpenId(String weixinOpenId){
        this.weixinOpenId = weixinOpenId;
    }
    public String getWeixinOpenId(){
        return this.weixinOpenId;
    }
    public void setWeiboOpenId(String weiboOpenId){
        this.weiboOpenId = weiboOpenId;
    }
    public String getWeiboOpenId(){
        return this.weiboOpenId;
    }
    public void setHeaderImg(String headerImg){
        this.headerImg = headerImg;
    }
    public String getHeaderImg(){
        return this.headerImg;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setSex(Integer sex){
        this.sex = sex;
    }
    public Integer getSex(){
        return this.sex;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getRealName(){
        return this.realName;
    }
    public void setUserStatus(Integer userStatus){
        this.userStatus = userStatus;
    }
    public Integer getUserStatus(){
        return this.userStatus;
    }
    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }
    public String getSchoolName(){
        return this.schoolName;
    }

    public Date getEnrollYear() {
        return enrollYear;
    }

    public void setEnrollYear(String enrollYear) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.enrollYear = sdf.parse(enrollYear);
        } catch (ParseException e) {
            this.enrollYear = null;
        }
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthday = sdf.parse(birthday);
        } catch (ParseException e) {
            this.birthday = null;
        }
    }

    public void setRegType(Integer regType){
        this.regType = regType;
    }
    public Integer getRegType(){
        return this.regType;
    }
    public void setIsPush(Integer isPush){
        this.isPush = isPush;
    }
    public Integer getIsPush(){
        return this.isPush;
    }
    public void setLastLoginDate(Date lastLoginDate){
        this.lastLoginDate = lastLoginDate;
    }
    public Date getLastLoginDate(){
        return this.lastLoginDate;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setIsAutoRemove(Integer isAutoRemove){
        this.isAutoRemove = isAutoRemove;
    }
    public Integer getIsAutoRemove(){
        return this.isAutoRemove;
    }

}
