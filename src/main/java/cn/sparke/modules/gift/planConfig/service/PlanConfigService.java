package cn.sparke.modules.gift.planConfig.service;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.gift.planConfig.entity.PlanConfigEntity;
import cn.sparke.modules.gift.planConfig.mapper.PlanConfigMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学习方案Dao
 *
 * @author spark
 * @Date 2017-08-19 10:33:52
 */
@Service
public class PlanConfigService{
    @Autowired
    private PlanConfigMapper planConfigMapper;

    public void save(PlanConfigEntity planConfig){
        planConfig.preInsert();
        //设置id
        planConfig.setId(ToolUtil.uuid());
        planConfigMapper.insert(planConfig);
    }

    public void update(PlanConfigEntity planConfig){
        planConfig.preUpdate();
        planConfigMapper.update(planConfig);
    }

    public PlanConfigEntity get(PlanConfigEntity planConfig){
        return planConfigMapper.get(planConfig);
    }

    public PlanConfigEntity getById(String id){
        return planConfigMapper.getById(id);
    }

    //学习方案列表
    public Page<PlanConfigEntity> findList(PlanConfigEntity planConfig){
       return planConfigMapper.findList(planConfig);
    }

    public void deleteById(String id){
      planConfigMapper.deleteById(id);
    }

}
