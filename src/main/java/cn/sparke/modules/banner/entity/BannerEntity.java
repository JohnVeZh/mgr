package cn.sparke.modules.banner.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * bannerEntity
 *
 * @author spark
 * @Date 2017-07-27 11:04:49
 */
public class BannerEntity extends BaseEntity{
    //标题
    private String title;
    //图片
    private String img;
    //终端类型(1. app 2.pc)
    private Integer terminalType;
    //模块类型（1.首页 2.资讯 3.网课）
    private Integer moduleType;
    //级别(1.4级 2.6级 3.考研 4.英专 5.高中英语 6.初中英语 7.小学英语)
    private Integer sectionCode;
    //跳转类型：0.无 1.富文本 2.外部链接 3.活动 4. 资讯5.图书 6.网课 
    private Integer jumpType;
    //外部链接地址
    private String url;
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
    public void setTerminalType(Integer terminalType){
        this.terminalType = terminalType;
    }
    public Integer getTerminalType(){
        return this.terminalType;
    }
    public void setModuleType(Integer moduleType){
        this.moduleType = moduleType;
    }
    public Integer getModuleType(){
        return this.moduleType;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
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
