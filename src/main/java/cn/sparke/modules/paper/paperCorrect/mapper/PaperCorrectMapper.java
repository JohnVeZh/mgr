package cn.sparke.modules.paper.paperCorrect.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paperCorrect.entity.PaperCorrectEntity;
import cn.sparke.modules.paper.paperCorrect.entity.RuleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 大礼包试卷批改Dao
 *
 * @author spark
 * @Date 2017-08-21 09:39:15
 */
public interface PaperCorrectMapper extends BaseMapper<PaperCorrectEntity>{

    //根据学段和作业类型获取规则
    List<RuleBean> getRules(@Param ("sectionCode") int sectionCode,@Param ("questionType") int questionType);
}
