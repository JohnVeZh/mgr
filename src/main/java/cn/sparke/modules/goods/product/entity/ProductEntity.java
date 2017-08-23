package cn.sparke.modules.goods.product.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 商品Entity
 *
 * @author spark
 * @Date 2017-07-19 15:58:00
 */
public class ProductEntity extends BaseEntity{
    //商品名称
    private String name;

    //前台bug
    private String nameLike;
    //商品简介
    private String brief;
    //商品的详细介绍
    private String content;
    //商品类型(1.网课 2.图书)
    private Integer type;
    //列表小图
    private String listImg;
    //封面图片，http的url
    private String coverImg;
    //详情的多图
    private String contentImgs;
    //级别(1.4级 2.6级 3.考研 4.英专 5.高中英语 6.初中英语 7.小学英语)
    private Integer sectionCode;
    //现价
    private BigDecimal presentPrice;
    //原价
    private BigDecimal originalPrice;
    //销量
    private Integer saleNum;
    //评价几颗星，1,2,3,4,5
    private Integer evaluateStar;
    //评价数量
    private Integer evaluateNum;
    //是否展示(0.否 1.是)
    private Integer isShow;
    //是否需要邮寄(0.否 1.是)
    private Integer isSend;
    //是否包邮，0否，1是
    private Integer isPostage;
    //是否促销，0否，1是
    private Integer isPromotion;
    //运费
    private BigDecimal postage;

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setBrief(String brief){
        this.brief = brief;
    }
    public String getBrief(){
        return this.brief;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setListImg(String listImg){
        this.listImg = listImg;
    }
    public String getListImg(){
        return this.listImg;
    }
    public void setCoverImg(String coverImg){
        this.coverImg = coverImg;
    }
    public String getCoverImg(){
        return this.coverImg;
    }
    public void setContentImgs(String contentImgs){
        this.contentImgs = contentImgs;
    }
    public String getContentImgs(){
        return this.contentImgs;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setPresentPrice(BigDecimal presentPrice){
        this.presentPrice = presentPrice;
    }
    public BigDecimal getPresentPrice(){
        return this.presentPrice;
    }
    public void setOriginalPrice(BigDecimal originalPrice){
        this.originalPrice = originalPrice;
    }
    public BigDecimal getOriginalPrice(){
        return this.originalPrice;
    }
    public void setSaleNum(Integer saleNum){
        this.saleNum = saleNum;
    }
    public Integer getSaleNum(){
        return this.saleNum;
    }
    public void setEvaluateStar(Integer evaluateStar){
        this.evaluateStar = evaluateStar;
    }
    public Integer getEvaluateStar(){
        return this.evaluateStar;
    }
    public void setEvaluateNum(Integer evaluateNum){
        this.evaluateNum = evaluateNum;
    }
    public Integer getEvaluateNum(){
        return this.evaluateNum;
    }
    public void setIsShow(Integer isShow){
        this.isShow = isShow;
    }
    public Integer getIsShow(){
        return this.isShow;
    }
    public void setIsSend(Integer isSend){
        this.isSend = isSend;
    }
    public Integer getIsSend(){
        return this.isSend;
    }
    public void setIsPostage(Integer isPostage){
        this.isPostage = isPostage;
    }
    public Integer getIsPostage(){
        return this.isPostage;
    }
    public void setIsPromotion(Integer isPromotion){
        this.isPromotion = isPromotion;
    }
    public Integer getIsPromotion(){
        return this.isPromotion;
    }
    public void setPostage(BigDecimal postage){
        this.postage = postage;
    }
    public BigDecimal getPostage(){
        return this.postage;
    }

}
