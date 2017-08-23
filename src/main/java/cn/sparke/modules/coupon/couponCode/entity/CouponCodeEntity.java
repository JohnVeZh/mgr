package cn.sparke.modules.coupon.couponCode.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 优惠码Entity
 *
 * @author spark
 * @Date 2017-07-26 14:22:24
 */
public class CouponCodeEntity extends BaseEntity{
    //优惠码
    @NotBlank(message = "填写优惠码")
    private String code;
    //已兑换次数
    @NotNull(message = "填写已兑换次数")
    private Integer usedNum;
    //最大可兑换次数：0不限制
    @NotNull(message = "填写最大可兑换次数")
    private Integer maxNum;
    //生效时间
    @NotNull(message = "填写生效时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    //失效时间
    @NotNull(message = "填写失效时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date invalidDate;
    //状态：0禁用、1正常
    @NotNull(message = "填写状态")
    private Integer status;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setUsedNum(Integer usedNum){
        this.usedNum = usedNum;
    }
    public Integer getUsedNum(){
        return this.usedNum;
    }
    public void setMaxNum(Integer maxNum){
        this.maxNum = maxNum;
    }
    public Integer getMaxNum(){
        return this.maxNum;
    }
    public void setEffectDate(Date effectDate){
        this.effectDate = effectDate;
    }
    public Date getEffectDate(){
        return this.effectDate;
    }
    public void setInvalidDate(Date invalidDate){
        this.invalidDate = invalidDate;
    }
    public Date getInvalidDate(){
        return this.invalidDate;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return this.status;
    }

}
