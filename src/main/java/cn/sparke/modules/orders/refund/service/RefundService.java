package cn.sparke.modules.orders.refund.service;

import cn.sparke.modules.orders.refund.entity.RefundDetailsEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.orders.refund.mapper.RefundMapper;
import cn.sparke.modules.orders.refund.entity.RefundEntity;

/**
 * 退单Dao
 *
 * @author spark
 * @Date 2017-07-20 09:54:27
 */
@Service
public class RefundService{

    @Autowired
    private RefundMapper refundMapper;

    /**
     * 查询列表
     * @param refundEntity
     * @return
     */
    public Page<RefundEntity> findList(RefundEntity refundEntity){

        return refundMapper.findList(refundEntity);
    }

    /**
     * 退单详情
     * @param refundId
     * @return
     */
    public RefundDetailsEntity getById(String refundId){

        return refundMapper.getById(refundId);
    }

}
