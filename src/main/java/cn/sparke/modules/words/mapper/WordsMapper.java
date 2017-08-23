package cn.sparke.modules.words.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.words.entity.WordsEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 词汇管理Dao
 *
 * @author spark
 * @Date 2017-07-25 20:49:20
 */
public interface WordsMapper extends BaseMapper<WordsEntity>{

    int insertSelective(WordsEntity wordsEntity);

    int deleteCatalogByCatalog(@Param("catalogId") String catalogId);
}
