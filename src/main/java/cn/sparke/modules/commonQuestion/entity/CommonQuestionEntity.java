package cn.sparke.modules.commonQuestion.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 常见问题Entity
 *
 * @author spark
 * @Date 2017-07-31 09:52:24
 */
public class CommonQuestionEntity extends BaseEntity{
    //问题标题
    private String title;
    //问题内容
    private String content;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }

}
