package cn.sparke.modules.goods.redeemCode.entity;

import cn.sparke.core.common.entity.BaseEntity;

import java.util.Date;

/**
 * 网课兑换码Entity
 *
 * @author spark
 * @Date 2017-07-21 17:17:55
 */
public class QueryVo extends RedeemCodeEntity{

    private String ncName;//网课名称
    private String mold;//1系统课，2公开课 3.微课
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNcName() {
        return ncName;
    }

    public void setNcName(String ncName) {
        this.ncName = ncName;
    }

    public String getMold() {
        return mold;
    }

    public void setMold(String mold) {
        this.mold = mold;
    }
}
