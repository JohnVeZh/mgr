package cn.sparke.modules.goods.redeemCode.mapper;


import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.redeemCode.entity.QueryVo;
import cn.sparke.modules.goods.redeemCode.entity.RedeemCodeEntity;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 网课兑换码Dao
 *
 * @author spark
 * @Date 2017-07-21 17:17:55
 */
public interface RedeemCodeMapper extends BaseMapper<RedeemCodeEntity> {

    //查询方法
    Page<QueryVo> queryCodeList(QueryVo vo);
    //批量插入
    void insertAll(List list);
}
