package cn.sparke.modules.coupon.couponCode.service;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.coupon.couponCode.entity.BatchCouponCodeVo;
import cn.sparke.modules.orders.utils.Utils;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.coupon.couponCode.mapper.CouponCodeMapper;
import cn.sparke.modules.coupon.couponCode.entity.CouponCodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠码Dao
 *
 * @author spark
 * @Date 2017-07-26 14:22:24
 */
@Service
public class CouponCodeService {
    @Autowired
    private CouponCodeMapper couponCodeMapper;

    public void save(CouponCodeEntity couponCode) {
        couponCode.preInsert();
        couponCodeMapper.insert(couponCode);
    }

    public void update(CouponCodeEntity couponCode) {
        couponCode.preUpdate();
        couponCodeMapper.update(couponCode);
    }

    public CouponCodeEntity get(CouponCodeEntity couponCode) {
        return couponCodeMapper.get(couponCode);
    }

    public CouponCodeEntity getById(String id) {
        return couponCodeMapper.getById(id);
    }

    public Page<CouponCodeEntity> findList(CouponCodeEntity couponCode) {
        return couponCodeMapper.findList(couponCode);
    }

    public void deleteById(String id) {
        couponCodeMapper.deleteById(id);
    }

    public void batchSave(BatchCouponCodeVo entity) {
        String entityStr = JSON.toJSONString(entity);
        List<CouponCodeEntity> couponCodeEntityList = new ArrayList<>();
        for (int i = 0; i < entity.getCodeNum(); i++) {
            String code = ToolUtil.uuid().substring(0, 6);
            CouponCodeEntity couponCodeEntity = JSON.parseObject(entityStr, CouponCodeEntity.class);
            couponCodeEntity.preInsert();
            couponCodeEntity.setCode(code);
            couponCodeEntityList.add(couponCodeEntity);
        }
        int line =  couponCodeMapper.insertList(couponCodeEntityList);
    }
}
