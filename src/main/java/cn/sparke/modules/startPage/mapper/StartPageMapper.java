package cn.sparke.modules.startPage.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.startPage.entity.StartPageEntity;

/**
 * 启动页Dao
 *
 * @author spark
 * @Date 2017-08-02 21:27:28
 */
public interface StartPageMapper extends BaseMapper<StartPageEntity>{
   int updateIsShow(StartPageEntity entity);

}
