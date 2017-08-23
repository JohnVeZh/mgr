package cn.sparke.modules.questionbank.question_item.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;

import java.util.List;

/**
 * 题目小题表Entity
 *
 * @author spark
 * @Date 2017-07-19 11:02:14
 */
public class Question_itemEntity extends BaseEntity{
    //题目id
    private String questionId;
    //小题内容
    private String content;
    //解析
    private String analysis;

    private String name;
    private Integer type;

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {

        return typeName;
    }

    private String typeName;

    //查询字段
    private List<String> questionIdList;

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }
    public String getQuestionId(){
        return this.questionId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setAnalysis(String analysis){
        this.analysis = analysis;
    }
    public String getAnalysis(){
        return this.analysis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getType() {
        return type;
    }

    public List<String> getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(List<String> questionIdList) {
        this.questionIdList = questionIdList;
    }
}
