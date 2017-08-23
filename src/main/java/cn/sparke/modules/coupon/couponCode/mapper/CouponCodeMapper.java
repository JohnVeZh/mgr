package cn.sparke.modules.coupon.couponCode.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.coupon.couponCode.entity.CouponCodeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠码Dao
 *
 * @author spark
 * @Date 2017-07-26 14:22:24
 */
public interface CouponCodeMapper extends BaseMapper<CouponCodeEntity>{

    int insertList(@Param("couponCodeEntityList") List<CouponCodeEntity> couponCodeEntityList);
}
