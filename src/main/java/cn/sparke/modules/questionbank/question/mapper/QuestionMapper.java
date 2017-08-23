package cn.sparke.modules.questionbank.question.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.questionbank.question.entity.PaperSubtitleEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import com.github.pagehelper.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 题目Dao
 *
 * @author spark
 * @Date 2017-07-19 12:30:16
 */
public interface QuestionMapper extends BaseMapper<QuestionEntity>{


    List<ZTreeNode> sectionTreeList(Map<String, Object> map);

    List<PaperEntity> findPaperList(QuestionEntity entity);

    List<PaperSubtitleEntity> findPaperQrList(QuestionEntity entity);

    List<ZTreeNode> paperStructureTree(Map<String, Object> map);

    List<ZTreeNode> paperStructureTrees(String id);

    List<Map<String,Object>> getCaptionSectionCodeByPaperId(String paperId);

    Map<String,Object> getType(String id);
    @Transactional
    int insertList(List<QuestionEntity> list);
    Map<String,Object> getSectionCodeByPaperId(String paperId);
}
