package cn.sparke.modules.coupon.couponGainScene.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.coupon.couponGainScene.entity.CouponGainSceneEntity;
import cn.sparke.modules.coupon.couponGainScene.entity.vo.CouponGainSceneVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠劵业务场景Dao
 *
 * @author spark
 * @Date 2017-07-26 16:15:50
 */
public interface CouponGainSceneMapper extends BaseMapper<CouponGainSceneEntity> {


    Page<CouponGainSceneEntity> findVoList(CouponGainSceneVo newSearch);

    int insertBatch(@Param("couponGainSceneEntityList") List<CouponGainSceneEntity> couponGainSceneEntityList);
}
