package cn.sparke.modules.paper.paperCorrect.service;

import cn.sparke.modules.paper.paperCorrect.entity.PaperCorrectEntity;
import cn.sparke.modules.paper.paperCorrect.entity.RuleBean;
import cn.sparke.modules.paper.paperCorrect.mapper.PaperCorrectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

import java.util.List;


/**
 * 大礼包试卷批改Dao
 *
 * @author spark
 * @Date 2017-08-21 09:39:15
 */
@Service
public class PaperCorrectService{
    @Autowired
    private PaperCorrectMapper paperCorrectMapper;

    public void save(PaperCorrectEntity paperCorrect){
        paperCorrect.preInsert();
        paperCorrectMapper.insert(paperCorrect);
    }

    public void update(PaperCorrectEntity paperCorrect){
        paperCorrect.preUpdate();
        paperCorrectMapper.update(paperCorrect);
    }

    public PaperCorrectEntity get(PaperCorrectEntity paperCorrect){
        return paperCorrectMapper.get(paperCorrect);
    }

    public PaperCorrectEntity getById(String id){
        return paperCorrectMapper.getById(id);
    }
    public Page<PaperCorrectEntity> findList(PaperCorrectEntity paperCorrect){
       return paperCorrectMapper.findList(paperCorrect);
    }

    public void deleteById(String id){
      paperCorrectMapper.deleteById(id);
    }

    //根据学段和作业类型获取规则
    public List<RuleBean> getRules(int sectionCode,  int questionType){
        return paperCorrectMapper.getRules(sectionCode,questionType);
    }

}
