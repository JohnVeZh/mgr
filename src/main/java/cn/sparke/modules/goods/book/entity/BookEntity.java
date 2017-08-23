package cn.sparke.modules.goods.book.entity;


import cn.sparke.modules.goods.product.entity.ProductEntity;

import java.util.*;
import java.math.*;

/**
 * 图书Entity
 *
 * @author spark
 * @Date 2017-07-19 16:05:35
 */
public class BookEntity extends ProductEntity{


    //商品ID
    private String productId;
    //出版社
    private String presss;
    //类型ID
    private String typeId;

    //非数据库字段
    private String bookId;
    private String bookTypeName;
    private String startDate;
    private String endDate;

    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }
    public void setPresss(String presss){
        this.presss = presss;
    }
    public String getPresss(){
        return this.presss;
    }
    public void setTypeId(String typeId){
        this.typeId = typeId;
    }
    public String getTypeId(){
        return this.typeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
