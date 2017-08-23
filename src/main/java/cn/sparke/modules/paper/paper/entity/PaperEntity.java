package cn.sparke.modules.paper.paper.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.core.common.utils.ToolUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 试卷Entity
 *
 * @author spark
 * @Date 2017-07-19 09:50:52
 */
public class PaperEntity extends BaseEntity{
    //目录id
    private String catalogId;
    private String catalogName;
    private Integer catalogType;

    //二维码代码
    private String qrCode;
    //名称
    private String name;
    //内容类型（0.不区分1.听力2.阅读3.翻译4.写作;默认0）
    private Integer contentType;
    //code
    private String code;
    //试卷图片
    private String img;
    //试卷内题目总数
    private Integer questionNum;

    //查询字段
    private String queryName;
    private String paperIds;
    private List<String> paperIdList;
    private List<String> catalogIdList;
    private String groupIds;


    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
    }
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }
    public String getQrCode(){
        return this.qrCode;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setContentType(Integer contentType){
        this.contentType = contentType;
    }
    public Integer getContentType(){
        return this.contentType;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setQuestionNum(Integer questionNum){
        this.questionNum = questionNum;
    }
    public Integer getQuestionNum(){
        return this.questionNum;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getPaperIds() {
        return paperIds;
    }

    public void setPaperIds(String paperIds) {
        this.paperIds = paperIds;
    }

    public List<String> getPaperIdList() {
        if (ToolUtil.isNotEmpty(this.paperIds))
            return Arrays.asList(this.paperIds.split(","));
        else
            return null;
    }

    public void setPaperIdList(List<String> paperIdList) {
        this.paperIdList = paperIdList;
    }

    public List<String> getCatalogIdList() {
        return catalogIdList;
    }

    public void setCatalogIdList(List<String> catalogIdList) {
        this.catalogIdList = catalogIdList;
    }

    public Integer getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(Integer catalogType) {
        this.catalogType = catalogType;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }
}
