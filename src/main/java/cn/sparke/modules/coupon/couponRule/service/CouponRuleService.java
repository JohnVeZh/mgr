package cn.sparke.modules.coupon.couponRule.service;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.modules.coupon.couponGainScene.entity.CouponGainSceneEntity;
import cn.sparke.modules.coupon.couponGainScene.mapper.CouponGainSceneMapper;
import cn.sparke.modules.coupon.couponRule.entity.CouponRuleEntity;
import cn.sparke.modules.coupon.couponRule.mapper.CouponRuleMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠劵规则Dao
 *
 * @author spark
 * @Date 2017-07-26 14:21:45
 */
@Service
public class CouponRuleService {
    @Autowired
    private CouponRuleMapper couponRuleMapper;
    @Autowired
    private CouponGainSceneMapper couponGainSceneMapper;

    public void save(CouponRuleEntity couponRule) {
        couponRule.preInsert();
        couponRuleMapper.insert(couponRule);
    }

    public void update(CouponRuleEntity couponRule) {
        couponRule.preUpdate();
        couponRuleMapper.update(couponRule);
    }

    public CouponRuleEntity get(CouponRuleEntity couponRule) {
        return couponRuleMapper.get(couponRule);
    }

    public CouponRuleEntity getById(String id) {
        return couponRuleMapper.getById(id);
    }

    public Page<CouponRuleEntity> findList(CouponRuleEntity couponRule) {
        return couponRuleMapper.findList(couponRule);
    }

    public void deleteById(String id) {
        CouponGainSceneEntity newSearch = new CouponGainSceneEntity();
        newSearch.setRuleId(id);
        newSearch.setDelFlag(0);
        Page list = couponGainSceneMapper.findList(newSearch);
        if (list != null && list.size() > 0){
            throw new BussinessException(BizExceptionEnum.COUPON_RULE_HAS_GAINSCENE);
        }
        couponRuleMapper.deleteById(id);
    }

}
