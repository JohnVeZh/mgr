package cn.sparke.modules.goods.redeemCode.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.orders.utils.annotation.ExcelField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExcelRedeemCodeEntity extends BaseEntity {
    //兑换码
    @ExcelField(title = "兑换码",order = 1)
    private String code;

    //网课名称
    @ExcelField(title = "网课名称",order = 2)
    private String ncName;

    //生效时间
    @ExcelField(title = "生效时间",order = 3)
    private String startTime;

    //失效时间
    @ExcelField(title = "失效时间",order = 4)
    private String endTime;
    //是否导出 0: 未导出, 1: 已导出
    @ExcelField(title = "是否导出",order = 5)
    private String exprotOrNot;
    //是否可用 0: 禁用, 1: 可用
    @ExcelField(title = "是否可用",order = 6)
    private String enableOrNot;
    //状态(0.未使用 1.已使用)
    @ExcelField(title = "状态",order = 7)
    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExprotOrNot() {
        return exprotOrNot;
    }

    public void setExprotOrNot(String exprotOrNot) {
        this.exprotOrNot = exprotOrNot;
    }

    public String getEnableOrNot() {
        return enableOrNot;
    }

    public void setEnableOrNot(String enableOrNot) {
        this.enableOrNot = enableOrNot;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNcName() {
        return ncName;
    }

    public void setNcName(String ncName) {
        this.ncName = ncName;
    }
}

