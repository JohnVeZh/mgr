package cn.sparke.modules.popUp.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.popUp.entity.PopUpEntity;

import java.util.List;

/**
 * 弹窗Dao
 *
 * @author spark
 * @Date 2017-08-19 14:02:51
 */
public interface PopUpMapper extends BaseMapper<PopUpEntity>{
    /**
     * 该展示模块起始时间内有效弹窗数量
     * @return
     */
    Integer selectCount(PopUpEntity popUpEntity);

    /**
     * 需要设置过期的弹窗列表
     * @return
     */
    List<PopUpEntity> getExpiredList();

    /**
     * 设置弹窗过期
     * @param id
     */
    void expired(String id);
}
