package cn.sparke.modules.popUp.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 弹窗Entity
 *
 * @author spark
 * @Date 2017-08-19 14:02:51
 */
public class PopUpEntity extends BaseEntity{
    //标题
    @NotBlank(message = "弹窗主题不能为空")
    private String title;
    //图片
    private String img;
    //跳转类型( 2.外部链接 3.首页 4.网课-四级 5.网课-六级 6.网课-考研 7.社区-资讯 8.社区-活动 9.图书 10.我的 )
    private Integer jumpType;
    //外部地址使用
    private String url;
    //开始时间
    @NotNull(message = "开始时间不能为空")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //结束时间
    @NotNull(message = "结束时间不能为空")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //展示模块(1首页，2社区，3商城，4我的 5网课(同一个模块同一个时间段只能有一个弹框))
    private Integer showModule;
    //状态(1.有效 2.失效)
    private Integer status;

    //非数据库字段
    private String operationName;

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
    public void setStartTime(String startTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.startTime = sdf.parse(startTime);
        } catch (ParseException e) {
            this.startTime = null;
        }
    }
    public Date getStartTime(){
        return this.startTime;
    }
    public void setEndTime(String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.endTime = sdf.parse(endTime);
        } catch (ParseException e) {
            this.endTime = null;
        }
    }
    public Date getEndTime(){
        return this.endTime;
    }
    public void setShowModule(Integer showModule){
        this.showModule = showModule;
    }
    public Integer getShowModule(){
        return this.showModule;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return this.status;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
