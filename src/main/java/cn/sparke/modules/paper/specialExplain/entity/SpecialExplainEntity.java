package cn.sparke.modules.paper.specialExplain.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 专项讲解Entity
 *
 * @author spark
 * @Date 2017-07-22 11:02:09
 */
public class SpecialExplainEntity extends BaseEntity{
    //标题
    private String title;
    //专项类型：1.听力2,阅读3.翻译4.写作5.词汇
    private Integer type;
    //学段
    private Integer sectionCode;
    //类型：1、自有内容、2外部链接、3、资讯、4、图书、5、网课
    private Integer contentType;
    //内容Id
    private String contentId;
    //内容：type=1_时有效
    private String content;
    //外部链接地址：type = 2时有效
    private String url;
    //访问次数
    private Long visitNum;

    //查询字段
    private String queryTitle;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setContentType(Integer contentType){
        this.contentType = contentType;
    }
    public Integer getContentType(){
        return this.contentType;
    }
    public void setContentId(String contentId){
        this.contentId = contentId;
    }
    public String getContentId(){
        return this.contentId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setVisitNum(Long visitNum){
        this.visitNum = visitNum;
    }
    public Long getVisitNum(){
        return this.visitNum;
    }

    public String getQueryTitle() {
        return queryTitle;
    }

    public void setQueryTitle(String queryTitle) {
        this.queryTitle = queryTitle;
    }
}
