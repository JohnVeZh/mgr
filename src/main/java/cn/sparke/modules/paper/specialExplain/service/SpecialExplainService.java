package cn.sparke.modules.paper.specialExplain.service;

import cn.sparke.core.common.utils.Convert;
import cn.sparke.modules.paper.specialExplain.entity.SpecialExplainEntity;
import cn.sparke.modules.paper.specialExplain.mapper.SpecialExplainMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 专项讲解Dao
 *
 * @author spark
 * @Date 2017-07-19 09:52:47
 */
@Service
public class SpecialExplainService{
    @Autowired
    private SpecialExplainMapper specialExplainMapper;

    public void save(SpecialExplainEntity specialExplain){
        specialExplain.setVisitNum(Convert.toLong(0));
        specialExplain.preInsert();
        specialExplainMapper.insert(specialExplain);
    }

    public void update(SpecialExplainEntity specialExplain){
        specialExplain.preUpdate();
        specialExplainMapper.update(specialExplain);
    }

    public SpecialExplainEntity get(SpecialExplainEntity specialExplain){
        return specialExplainMapper.get(specialExplain);
    }

    public SpecialExplainEntity getById(String id){
        return specialExplainMapper.getById(id);
    }
    public Page<SpecialExplainEntity> findList(SpecialExplainEntity specialExplain){
       return specialExplainMapper.findList(specialExplain);
    }

    public void deleteById(String id){
      specialExplainMapper.deleteById(id);
    }

}
