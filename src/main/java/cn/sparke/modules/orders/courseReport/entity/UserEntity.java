package cn.sparke.modules.orders.courseReport.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 用户Entity
 *
 * @author spark
 * @Date 2017-07-19 09:50:06
 */
public class UserEntity extends BaseEntity{
    //手机号
    private String phone;
    //用户头像
    private String headerImg;
    //用户昵称
    private String nickname;
    //真实姓名
    private String realName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
