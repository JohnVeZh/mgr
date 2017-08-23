package cn.sparke.modules.reportStatistics.trafficStatistic.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.reportStatistics.trafficStatistic.entity.TrafficStatisticEntity;
import cn.sparke.modules.reportStatistics.trafficStatistic.entity.ValueEntity;

import java.util.List;

/**
 * 流量统计Dao
 *
 * @author spark
 * @Date 2017-08-01 10:03:56
 */
public interface TrafficStatisticMapper extends BaseMapper<TrafficStatisticEntity>{
    List<ValueEntity> getOSPVUV(TrafficStatisticEntity entity);
    List<ValueEntity> getAddressPVUV(TrafficStatisticEntity entity);
}
