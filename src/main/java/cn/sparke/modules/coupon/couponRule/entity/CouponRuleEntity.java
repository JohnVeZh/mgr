package cn.sparke.modules.coupon.couponRule.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠劵规则Entity
 *
 * @author spark
 * @Date 2017-07-26 14:21:45
 */
public class CouponRuleEntity extends BaseEntity {
    //生成的优惠券名称
    private String title;
    //生效时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    //失效时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date invalidDate;
    //最低使用金额
    private BigDecimal minMoney;
    //折扣金额
    private BigDecimal discountMoney;
    //规则状态：0禁用、1正常
    private Integer status;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getEffectDate() {
        return this.effectDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Date getInvalidDate() {
        return this.invalidDate;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMinMoney() {
        return this.minMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getDiscountMoney() {
        return this.discountMoney;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

}
