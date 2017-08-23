package cn.sparke.modules.commonQuestion.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.commonQuestion.mapper.CommonQuestionMapper;
import cn.sparke.modules.commonQuestion.entity.CommonQuestionEntity;

/**
 * 常见问题Dao
 *
 * @author spark
 * @Date 2017-07-31 09:52:24
 */
@Service
public class CommonQuestionService{
    @Autowired
    private CommonQuestionMapper commonQuestionMapper;

    public void save(CommonQuestionEntity commonQuestion){
        commonQuestion.preInsert();
        commonQuestionMapper.insert(commonQuestion);
    }

    public void update(CommonQuestionEntity commonQuestion){
        commonQuestion.preUpdate();
        commonQuestionMapper.update(commonQuestion);
    }

    public CommonQuestionEntity get(CommonQuestionEntity commonQuestion){
        return commonQuestionMapper.get(commonQuestion);
    }

    public CommonQuestionEntity getById(String id){
        return commonQuestionMapper.getById(id);
    }
    public Page<CommonQuestionEntity> findList(CommonQuestionEntity commonQuestion){
       return commonQuestionMapper.findList(commonQuestion);
    }

    public void deleteById(String id){
      commonQuestionMapper.deleteById(id);
    }

}
