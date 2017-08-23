package cn.sparke.modules.paper.paperQuestion.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 试卷题目关系表Entity
 *
 * @author spark
 * @Date 2017-07-25 19:42:26
 */
public class PaperQuestionEntity extends BaseEntity{
    //试卷id
    private String paperId;
    //结构id
    private String structureId;
    //题目id
    private String questionId;

    //查询字段
    private List<String> paperIdList;

    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
    public void setStructureId(String structureId){
        this.structureId = structureId;
    }
    public String getStructureId(){
        return this.structureId;
    }
    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }
    public String getQuestionId(){
        return this.questionId;
    }

    public List<String> getPaperIdList() {
        return paperIdList;
    }

    public void setPaperIdList(List<String> paperIdList) {
        this.paperIdList = paperIdList;
    }
}
