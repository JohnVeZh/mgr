package cn.sparke.modules.system.dict.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.system.dict.entity.DictEntity;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public interface DictMapper extends BaseMapper<DictEntity> {
    List<DictEntity> existDict(DictEntity dictEntity);

    void deleteRealById(String id);

    List<DictEntity> findAllList();

    <R, P> Page<R> findChannelList(DictEntity entity);
}
