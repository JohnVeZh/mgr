package cn.sparke.modules.coupon.couponGainScene.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 优惠劵业务场景Entity
 *
 * @author spark
 * @Date 2017-07-26 16:15:50
 */
public class CouponGainSceneEntity extends BaseEntity{
    //规则id
    private String ruleId;

    private String ruleName;
    //场景类型(1.商品 2.优惠码 3.活动 4.首页banner )
    private String type;
    //场景id(商品id、优惠码id、活动id、bannerid)
    private String contentId;

    private String contentName;
    //优惠券作用的商品id
    private String productId;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setRuleId(String ruleId){
        this.ruleId = ruleId;
    }
    public String getRuleId(){
        return this.ruleId;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setContentId(String contentId){
        this.contentId = contentId;
    }
    public String getContentId(){
        return this.contentId;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }

}
