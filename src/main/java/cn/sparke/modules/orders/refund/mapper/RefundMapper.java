package cn.sparke.modules.orders.refund.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.orders.refund.entity.RefundDetailsEntity;
import cn.sparke.modules.orders.refund.entity.RefundEntity;
import com.github.pagehelper.Page;

/**
 * 退单Dao
 *
 * @author spark
 * @Date 2017-07-20 09:54:27
 */
public interface RefundMapper extends BaseMapper{

    /**
     * 退单列表
     * @param refundEntity
     * @return
     */
    Page<RefundEntity> findList(RefundEntity refundEntity);

    /**
     * 退单详情
     * @param refundId
     * @return
     */
    RefundDetailsEntity getById(String refundId);

}
