package cn.sparke.modules.questionbank.question_option.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 题目选项Entity
 *
 * @author spark
 * @Date 2017-07-19 11:23:46
 */
public class Question_optionEntity extends BaseEntity{
    //问题id
    private String itemId;
    //选项名称(A B C D)
    private String name;
    //选项内容
    private String content;
    //是否为正确答案(0.否 1.是)
    private Integer isAnswer;

    public void setItemId(String itemId){
        this.itemId = itemId;
    }
    public String getItemId(){
        return this.itemId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setIsAnswer(Integer isAnswer){
        this.isAnswer = isAnswer;
    }
    public Integer getIsAnswer(){
        return this.isAnswer;
    }

}
