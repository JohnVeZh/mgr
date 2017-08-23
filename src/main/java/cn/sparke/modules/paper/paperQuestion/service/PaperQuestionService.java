package cn.sparke.modules.paper.paperQuestion.service;

import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 试卷题目关系表Dao
 *
 * @author spark
 * @Date 2017-07-25 19:42:26
 */
@Service
public class PaperQuestionService{
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    public void save(PaperQuestionEntity paperQuestion){
        paperQuestion.preInsert();
        paperQuestionMapper.insert(paperQuestion);
    }

    public void update(PaperQuestionEntity paperQuestion){
        paperQuestion.preUpdate();
        paperQuestionMapper.update(paperQuestion);
    }

    public PaperQuestionEntity get(PaperQuestionEntity paperQuestion){
        return paperQuestionMapper.get(paperQuestion);
    }

    public PaperQuestionEntity getById(String id){
        return paperQuestionMapper.getById(id);
    }
    public Page<PaperQuestionEntity> findList(PaperQuestionEntity paperQuestion){
       return paperQuestionMapper.findList(paperQuestion);
    }

    public void deleteById(String id){
      paperQuestionMapper.deleteById(id);
    }

}
