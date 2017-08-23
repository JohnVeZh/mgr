package cn.sparke.modules.coupon.couponCode.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/8/2 15:22
 */
public class BatchCouponCodeVo {

    @NotNull(message = "填写生成优惠码的数量")
    private Integer codeNum;
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

    public Integer getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(Integer codeNum) {
        this.codeNum = codeNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
