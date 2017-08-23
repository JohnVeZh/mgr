package cn.sparke.modules.gift.activationCode.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 激活码管理Entity
 *
 * @author spark
 * @Date 2017-08-22 09:48:21
 */
public class ActivationCodeEntity extends BaseEntity{

    private String code; // 激活码，8位，唯一
    private Integer sectionCode; // 学段
    private Integer isActivated; // 是否已经激活，0否，1是
    private Date activateTime; // 激活时间
    private String activateUserId; // 激活用户id
    private String address; // 激活地址

    private String phone; // 用户手机号
    private SubjectiveSubmitRecordBean subjectiveSubmitRecord; // 主观题提交记录

    // 查询字段
    private String qCode;
    private Integer qSectionCode;
    private String qPhone;
    private String qAddress;
    private Integer qIsActivated;
    private String qImportStartTime;
    private String qImportEndTime;
    private String qActivateStartTime;
    private String qActivateEndTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Integer isActivated) {
        this.isActivated = isActivated;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public String getActivateUserId() {
        return activateUserId;
    }

    public void setActivateUserId(String activateUserId) {
        this.activateUserId = activateUserId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SubjectiveSubmitRecordBean getSubjectiveSubmitRecord() {
        return subjectiveSubmitRecord;
    }

    public void setSubjectiveSubmitRecord(SubjectiveSubmitRecordBean subjectiveSubmitRecord) {
        this.subjectiveSubmitRecord = subjectiveSubmitRecord;
    }

    public String getqCode() {
        return qCode;
    }

    public void setqCode(String qCode) {
        this.qCode = qCode;
    }

    public Integer getqSectionCode() {
        return qSectionCode;
    }

    public void setqSectionCode(Integer qSectionCode) {
        this.qSectionCode = qSectionCode;
    }

    public String getqPhone() {
        return qPhone;
    }

    public void setqPhone(String qPhone) {
        this.qPhone = qPhone;
    }

    public String getqAddress() {
        return qAddress;
    }

    public void setqAddress(String qAddress) {
        this.qAddress = qAddress;
    }

    public Integer getqIsActivated() {
        return qIsActivated;
    }

    public void setqIsActivated(Integer qIsActivated) {
        this.qIsActivated = qIsActivated;
    }

    public String getqImportStartTime() {
        return qImportStartTime;
    }

    public void setqImportStartTime(String qImportStartTime) {
        this.qImportStartTime = qImportStartTime;
    }

    public String getqImportEndTime() {
        return qImportEndTime;
    }

    public void setqImportEndTime(String qImportEndTime) {
        this.qImportEndTime = qImportEndTime;
    }

    public String getqActivateStartTime() {
        return qActivateStartTime;
    }

    public void setqActivateStartTime(String qActivateStartTime) {
        this.qActivateStartTime = qActivateStartTime;
    }

    public String getqActivateEndTime() {
        return qActivateEndTime;
    }

    public void setqActivateEndTime(String qActivateEndTime) {
        this.qActivateEndTime = qActivateEndTime;
    }
}
