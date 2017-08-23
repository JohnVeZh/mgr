package cn.sparke.modules.questionbank.question_reading.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 阅读题Entity
 *
 * @author spark
 * @Date 2017-07-19 10:55:02
 */
public class Question_readingEntity extends BaseEntity{
    //题目id
    private String questionId;
    //译文
    private String translation;
    //是否是小题
    private Integer hasItem;
    //题目名称
    private String name;
    //题目类型
    private Integer type;

    //题干
    private  String content;
    //题目数量
    private Integer questionNum;
    //学段code
    private  Integer sectionCode;
    //解析
    private String analysis;


    //试卷id
    private String paperId;

    //结构id
    private  String structureId;

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getStructureId() {

        return structureId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperId() {

        return paperId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }


    public String getContent() {

        return content;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public Integer getType() {
        return type;
    }

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }
    public String getQuestionId(){
        return this.questionId;
    }
    public void setTranslation(String translation){
        this.translation = translation;
    }
    public String getTranslation(){
        return this.translation;
    }
    public void setHasItem(Integer hasItem){this.hasItem = hasItem;}
    public Integer getHasItem(){return this.hasItem;}

}
