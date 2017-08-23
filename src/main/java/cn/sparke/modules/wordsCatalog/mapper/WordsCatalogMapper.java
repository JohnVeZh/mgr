package cn.sparke.modules.wordsCatalog.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.wordsCatalog.entity.WordsCatalogEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 词汇栏目管理Dao
 *
 * @author spark
 * @Date 2017-07-25 19:51:11
 */
public interface WordsCatalogMapper extends BaseMapper<WordsCatalogEntity>{

    int insertSelective(WordsCatalogEntity wordsCatalogEntity);

    int countWordNum(@Param("catalogId") String catalogId);
}
