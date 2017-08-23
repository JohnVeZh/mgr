package cn.sparke.modules.startPage.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 启动页Entity
 *
 * @author spark
 * @Date 2017-08-02 21:27:28
 */
public class StartPageEntity extends BaseEntity{
    //标题
    private String title;
    //图片
    private String img;
    //跳转类型：0.无 1.富文本 2.外部链接 3.活动 4. 资讯5.图书 6.网课 
    private Integer jumpType;
    //外部链接地址
    private String url;
    //是否展示（0、隐藏1、展示）
    private Integer isShow;
    //内容id(资讯id、活动id、图书id、网课id)
    private String contentId;
    //内容名称(资讯标题、活动标题、图书名称、网课名称)
    private String contentTitle;
    //内容
    private String content;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setJumpType(Integer jumpType){
        this.jumpType = jumpType;
    }
    public Integer getJumpType(){
        return this.jumpType;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setIsShow(Integer isShow){
        this.isShow = isShow;
    }
    public Integer getIsShow(){
        return this.isShow;
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

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }
}
