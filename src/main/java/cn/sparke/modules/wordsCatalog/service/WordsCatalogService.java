package cn.sparke.modules.wordsCatalog.service;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.modules.words.mapper.WordsMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.wordsCatalog.mapper.WordsCatalogMapper;
import cn.sparke.modules.wordsCatalog.entity.WordsCatalogEntity;

/**
 * 词汇栏目管理Dao
 *
 * @author spark
 * @Date 2017-07-25 19:51:11
 */
@Service
public class WordsCatalogService {

    @Autowired
    private WordsMapper wordsMapper;
    @Autowired
    private WordsCatalogMapper wordsCatalogMapper;

    public void save(WordsCatalogEntity wordsCatalog) {
        wordsCatalog.preInsert();
        wordsCatalogMapper.insertSelective(wordsCatalog);
    }

    public void update(WordsCatalogEntity wordsCatalog) {
        wordsCatalog.preUpdate();
        wordsCatalogMapper.update(wordsCatalog);
    }

    public WordsCatalogEntity get(WordsCatalogEntity wordsCatalog) {
        return wordsCatalogMapper.get(wordsCatalog);
    }

    public WordsCatalogEntity getById(String id) {
        return wordsCatalogMapper.getById(id);
    }

    public Page<WordsCatalogEntity> findList(WordsCatalogEntity wordsCatalog) {
        return wordsCatalogMapper.findList(wordsCatalog);
    }

    public void deleteById(String id) {
        WordsCatalogEntity newSearch = new WordsCatalogEntity();
        newSearch.setParentId(id);
        newSearch.setDelFlag(0);
        Page page = wordsCatalogMapper.findList(newSearch);
        if (page != null && page.size() > 0) {
            throw new BussinessException(BizExceptionEnum.WORD_CATALOG_HAS_CHILD);
        }
        wordsMapper.deleteCatalogByCatalog(id);
        wordsCatalogMapper.deleteById(id);
    }

}
