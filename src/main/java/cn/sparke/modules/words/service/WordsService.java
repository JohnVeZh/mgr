package cn.sparke.modules.words.service;

import cn.sparke.modules.wordsCatalog.mapper.WordsCatalogMapper;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.words.mapper.WordsMapper;
import cn.sparke.modules.words.entity.WordsEntity;

/**
 * 词汇管理Dao
 *
 * @author spark
 * @Date 2017-07-25 20:49:20
 */
@Service
public class WordsService{
    @Autowired
    private WordsMapper wordsMapper;

    @Autowired
    private WordsCatalogMapper wordsCatalogMapper;

    public void save(WordsEntity words){
        words.preInsert();
        wordsMapper.insertSelective(words);
    }

    public void update(WordsEntity words){
        words.preUpdate();
        wordsMapper.update(words);
        if (!Strings.isNullOrEmpty(words.getCatalogId())) {
            wordsCatalogMapper.countWordNum(words.getCatalogId());
        }
    }

    public WordsEntity get(WordsEntity words){
        return wordsMapper.get(words);
    }

    public WordsEntity getById(String id){
        return wordsMapper.getById(id);
    }
    public Page<WordsEntity> findList(WordsEntity words){
       return wordsMapper.findList(words);
    }

    public void deleteById(String id){
      wordsMapper.deleteById(id);
    }

}
