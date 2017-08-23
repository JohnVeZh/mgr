package cn.sparke.modules.coupon.couponGainScene.entity.vo;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.coupon.couponGainScene.entity.CouponGainSceneEntity;

import java.util.List;

/**
 */
public class CouponGainSceneVo extends CouponGainSceneEntity {

    //优惠券作用的商品id
    private List<String> productIdList;

    public List<String> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }
}
