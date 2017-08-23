package cn.sparke.modules.questionError.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 题目报错Entity
 *
 * @author spark
 * @Date 2017-07-31 10:58:47
 */
public class QuestionErrorEntity extends BaseEntity{
    //用户ID
    private String userId;
    //学段
    private Integer sectionCode;
    //试卷id
    private String paperId;
    //题目id
    private String questionId;
    //问题内容
    private String content;
    //是否已解决(0.未解决 1，解决)
    private Integer isSolved;
    //类型(1.全真考场 2.字幕听力 3.简系列 4.专项练习)
    private Integer type;

    //拓展
    //题目名称
    private String questionName;
    //用户昵称
    private String nickname;
    //用户手机号
    private String phone;
    //结构名称
    private String structureName;
    //题目类型
    private Integer questionType;

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
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
    public void setIsSolved(Integer isSolved){
        this.isSolved = isSolved;
    }
    public Integer getIsSolved(){
        return this.isSolved;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }
}
