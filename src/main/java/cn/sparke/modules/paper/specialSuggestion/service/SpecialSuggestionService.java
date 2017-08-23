package cn.sparke.modules.paper.specialSuggestion.service;

import cn.sparke.modules.paper.specialSuggestion.entity.SpecialSuggestionEntity;
import cn.sparke.modules.paper.specialSuggestion.mapper.SpecialSuggestionMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分析建议Dao
 *
 * @author spark
 * @Date 2017-07-19 09:53:09
 */
@Service
public class SpecialSuggestionService{
    @Autowired
    private SpecialSuggestionMapper specialSuggestionMapper;

    public void save(SpecialSuggestionEntity specialSuggestion){
        specialSuggestion.preInsert();
        specialSuggestionMapper.insert(specialSuggestion);
    }

    public void update(SpecialSuggestionEntity specialSuggestion){
        specialSuggestion.preUpdate();
        specialSuggestionMapper.update(specialSuggestion);
    }

    public SpecialSuggestionEntity get(SpecialSuggestionEntity specialSuggestion){
        return specialSuggestionMapper.get(specialSuggestion);
    }

    public SpecialSuggestionEntity getById(String id){
        return specialSuggestionMapper.getById(id);
    }
    public Page<SpecialSuggestionEntity> findList(SpecialSuggestionEntity specialSuggestion){
       return specialSuggestionMapper.findList(specialSuggestion);
    }

    public void deleteById(String id){
      specialSuggestionMapper.deleteById(id);
    }

}
