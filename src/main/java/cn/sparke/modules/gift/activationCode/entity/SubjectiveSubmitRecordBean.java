package cn.sparke.modules.gift.activationCode.entity;


import cn.sparke.core.common.entity.BaseEntity;

/**
 * @author yangye    2017-08-18
 */
public class SubjectiveSubmitRecordBean extends BaseEntity {

    private String userId;
    private int sectionCode;
    private String codeId;

    private int preTranslateTotal;
    private int preTranslateUse;
    private int preWriteTotal;
    private int preWriteUse;
    private int midTranslateTotal;
    private int midTranslateUse;
    private int midWriteTotal;
    private int midWriteUse;
    private int postTranslateTotal;
    private int postTranslateUse;
    private int postWriteTotal;
    private int postWriteUse;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(int sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public int getPreTranslateTotal() {
        return preTranslateTotal;
    }

    public void setPreTranslateTotal(int preTranslateTotal) {
        this.preTranslateTotal = preTranslateTotal;
    }

    public int getPreTranslateUse() {
        return preTranslateUse;
    }

    public void setPreTranslateUse(int preTranslateUse) {
        this.preTranslateUse = preTranslateUse;
    }

    public int getPreWriteTotal() {
        return preWriteTotal;
    }

    public void setPreWriteTotal(int preWriteTotal) {
        this.preWriteTotal = preWriteTotal;
    }

    public int getPreWriteUse() {
        return preWriteUse;
    }

    public void setPreWriteUse(int preWriteUse) {
        this.preWriteUse = preWriteUse;
    }

    public int getMidTranslateTotal() {
        return midTranslateTotal;
    }

    public void setMidTranslateTotal(int midTranslateTotal) {
        this.midTranslateTotal = midTranslateTotal;
    }

    public int getMidTranslateUse() {
        return midTranslateUse;
    }

    public void setMidTranslateUse(int midTranslateUse) {
        this.midTranslateUse = midTranslateUse;
    }

    public int getMidWriteTotal() {
        return midWriteTotal;
    }

    public void setMidWriteTotal(int midWriteTotal) {
        this.midWriteTotal = midWriteTotal;
    }

    public int getMidWriteUse() {
        return midWriteUse;
    }

    public void setMidWriteUse(int midWriteUse) {
        this.midWriteUse = midWriteUse;
    }

    public int getPostTranslateTotal() {
        return postTranslateTotal;
    }

    public void setPostTranslateTotal(int postTranslateTotal) {
        this.postTranslateTotal = postTranslateTotal;
    }

    public int getPostTranslateUse() {
        return postTranslateUse;
    }

    public void setPostTranslateUse(int postTranslateUse) {
        this.postTranslateUse = postTranslateUse;
    }

    public int getPostWriteTotal() {
        return postWriteTotal;
    }

    public void setPostWriteTotal(int postWriteTotal) {
        this.postWriteTotal = postWriteTotal;
    }

    public int getPostWriteUse() {
        return postWriteUse;
    }

    public void setPostWriteUse(int postWriteUse) {
        this.postWriteUse = postWriteUse;
    }
}
