package cn.sparke.modules.questionbank.question.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 题目Entity
 *
 * @author spark
 * @Date 2017-07-19 12:30:16
 */
public class QuestionEntity extends BaseEntity{
    //题目名称
    private String name;
    //目录id
    private String catalogId;

    //题干
    private String content;
    //题目类型(1.听力 2.阅读 3.翻译 4.写作)
    private Integer type;
    //题目类型名称
    private String typeName;
    //是否存在小题(0.否 1.是)
    private Integer hasItem;
    //题目数量
    private Integer questionNum;
    //学段code
    private Integer sectionCode;
    //学段名称
    private String sectionCodeName;
    //解析
    private String analysis;

    //试卷id
    private String paperId;
    //结构id
    private String structureId;

    //别名(写作 阅读 section one)
    private String alias ;
    //试卷树结构的名称

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getAlias() {

        return alias;
    }

    public String getStructureName() {
        return structureName;
    }

    private String structureName;

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getStructureId() {

        return structureId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    //查询字段
    private String queryName;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }



    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setType(Integer type){
        this.type = type;
        if(type != null && !type.equals("")){
            switch (type){
                case 1:
                    this.typeName=QuestionContentType.CONTENT_LISTENING_NAME;
                    break;
                case 2:
                    this.typeName=QuestionContentType.CONTENT_READING_NAME;
                    break;
                case 3:
                    this.typeName=QuestionContentType.CONTENT_TRANSLATION_NAME;
                    break;
                case 4:
                    this.typeName=QuestionContentType.CONTENT_WRITING_NAME;
                    break;

            }
        }
    }
    public Integer getType(){
        return this.type;
    }
    public void setHasItem(Integer hasItem){
        this.hasItem = hasItem;
    }
    public Integer getHasItem(){
        return this.hasItem;
    }
    public void setQuestionNum(Integer questionNum){
        this.questionNum = questionNum;
    }
    public Integer getQuestionNum(){
        return this.questionNum;
    }
    public void setSectionCode(Integer sectionCode){


        this.sectionCode = sectionCode;
        if(sectionCode != null && !sectionCode.equals("")){
            switch (sectionCode){
                case 1:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_CET4_ENGLISH_NAME;
                    break;
                case 2:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_CET6_ENGLISH_NAME;
                    break;
                case 3:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_GRADUATE_ENGLISH_NAME;
                    break;
                case 4:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_MAJOR_ENGLISH_NAME;
                    break;
                case 5:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_SENIOR_ENGLISH_NAME;
                    break;
                case 6:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_JUNIOR_ENGLISH_NAME;
                    break;
                case 7:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_PRIMARY_ENGLISH_NAME;
                    break;
            }
        }
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setAnalysis(String analysis){
        this.analysis = analysis;
    }
    public String getAnalysis(){
        return this.analysis;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

}
