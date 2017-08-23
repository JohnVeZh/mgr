package cn.sparke.modules.paper.paper.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import com.github.pagehelper.Page;

/**
 * 试卷Dao
 *
 * @author spark
 * @Date 2017-07-19 09:50:52
 */
public interface PaperMapper extends BaseMapper<PaperEntity>{

    <R, P> Page<R> list(P entity);

    <R, P> Page<R> queryList(P entity);

}
