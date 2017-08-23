package cn.sparke.modules.coupon.couponGainScene.service;

import cn.sparke.modules.coupon.couponGainScene.entity.vo.CouponGainSceneVo;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.coupon.couponGainScene.mapper.CouponGainSceneMapper;
import cn.sparke.modules.coupon.couponGainScene.entity.CouponGainSceneEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠劵业务场景Dao
 *
 * @author spark
 * @Date 2017-07-26 16:15:50
 */
@Service
public class CouponGainSceneService {
    @Autowired
    private CouponGainSceneMapper couponGainSceneMapper;

    public void save(CouponGainSceneEntity couponGainScene) {
        couponGainScene.preInsert();
        couponGainSceneMapper.insert(couponGainScene);
    }

    public void update(CouponGainSceneEntity couponGainScene) {
        couponGainScene.preUpdate();
        couponGainSceneMapper.update(couponGainScene);
    }

    public CouponGainSceneEntity get(CouponGainSceneEntity couponGainScene) {
        return couponGainSceneMapper.get(couponGainScene);
    }

    public CouponGainSceneEntity getById(String id) {
        return couponGainSceneMapper.getById(id);
    }

    public Page<CouponGainSceneEntity> findList(CouponGainSceneEntity couponGainScene) {
        return couponGainSceneMapper.findList(couponGainScene);
    }

    public void deleteById(String id) {
        couponGainSceneMapper.deleteById(id);
    }

    public Page<CouponGainSceneEntity> findVoList(CouponGainSceneVo newSearch) {
        return couponGainSceneMapper.findVoList(newSearch);
    }

    public void batchSave(CouponGainSceneVo entity) {
        String couponGainSceneVoStr = JSON.toJSONString(entity);
        List<CouponGainSceneEntity> couponGainSceneEntityList = new ArrayList<>();
        entity.getProductIdList().forEach(productId -> {
            CouponGainSceneEntity couponGainSceneEntityDb = JSON.parseObject(couponGainSceneVoStr, CouponGainSceneEntity.class);
            couponGainSceneEntityDb.preInsert();
            couponGainSceneEntityDb.setProductId(productId);
            couponGainSceneEntityList.add(couponGainSceneEntityDb);
        });
        couponGainSceneMapper.insertBatch(couponGainSceneEntityList);

    }
}
