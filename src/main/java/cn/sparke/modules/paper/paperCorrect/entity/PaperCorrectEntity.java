package cn.sparke.modules.paper.paperCorrect.entity;

import cn.sparke.core.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;
import java.math.*;

/**
 * 大礼包试卷批改Entity
 *
 * @author spark
 * @Date 2017-08-21 09:39:15
 */
public class PaperCorrectEntity extends BaseEntity{
    //诊断报告id
    private String reportId;
    //试卷id
    private String paperId;
    //用户id
    private String userId;
    //1学前，2学中，3学末
    private Integer period;
    //题目类型，1听力，2阅读，3翻译，4写作
    private Integer questionType;
    //题号，1,2,3
    private Integer questionNo;
    //正确答案,听力和阅读的正确答案，翻译的译文，写作的范文
    private String rightAnswer;
    //用户答案：客观题选项、老师批改的主观题是上传的图片地址、自主评分的主观题为空
    private String userAnswer;
    //是否需要老师批改:0否（自主评分），1是（针对翻译和写作）
    private Integer isTeacherEvaluate;
    //得分
    private Integer score;
    //老师的批改内容
    private String replyContent;
    //批改老师的ID
    private String replyUserId;

    //批改状态
    private Integer replyStatus;
    //批改时间
    @DateTimeFormat (pattern="yyyy-MM-dd HH:mm:ss")
    private Date replyDate;

    //用户手机
    private String phone;
    //试卷学段
    private Integer sectionCode;
    //批改人
    private String teacherName;

    private List<RuleBean> ruleBeans;


    //非数据库字段
    private String commitTimeStart;//提交时间-开始
    private String commitTimeEnd;//提交时间-结束
    private String correctTimeStart;//批改时间-开始
    private String correctTimeEnd;//批改时间-结束

    public List<RuleBean> getRuleBeans() {
        return ruleBeans;
    }

    public void setRuleBeans(List<RuleBean> ruleBeans) {
        this.ruleBeans = ruleBeans;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getCommitTimeStart() {
        return commitTimeStart;
    }

    public void setCommitTimeStart(String commitTimeStart) {
        this.commitTimeStart = commitTimeStart;
    }

    public String getCommitTimeEnd() {
        return commitTimeEnd;
    }

    public void setCommitTimeEnd(String commitTimeEnd) {
        this.commitTimeEnd = commitTimeEnd;
    }

    public String getCorrectTimeStart() {
        return correctTimeStart;
    }

    public void setCorrectTimeStart(String correctTimeStart) {
        this.correctTimeStart = correctTimeStart;
    }

    public String getCorrectTimeEnd() {
        return correctTimeEnd;
    }

    public void setCorrectTimeEnd(String correctTimeEnd) {
        this.correctTimeEnd = correctTimeEnd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setReportId(String reportId){
        this.reportId = reportId;
    }
    public String getReportId(){
        return this.reportId;
    }
    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setPeriod(Integer period){
        this.period = period;
    }
    public Integer getPeriod(){
        return this.period;
    }
    public void setQuestionType(Integer questionType){
        this.questionType = questionType;
    }
    public Integer getQuestionType(){
        return this.questionType;
    }
    public void setQuestionNo(Integer questionNo){
        this.questionNo = questionNo;
    }
    public Integer getQuestionNo(){
        return this.questionNo;
    }
    public void setRightAnswer(String rightAnswer){
        this.rightAnswer = rightAnswer;
    }
    public String getRightAnswer(){
        return this.rightAnswer;
    }
    public void setUserAnswer(String userAnswer){
        this.userAnswer = userAnswer;
    }
    public String getUserAnswer(){
        return this.userAnswer;
    }
    public void setIsTeacherEvaluate(Integer isTeacherEvaluate){
        this.isTeacherEvaluate = isTeacherEvaluate;
    }
    public Integer getIsTeacherEvaluate(){
        return this.isTeacherEvaluate;
    }
    public void setScore(Integer score){
        this.score = score;
    }
    public Integer getScore(){
        return this.score;
    }
    public void setReplyContent(String replyContent){
        this.replyContent = replyContent;
    }
    public String getReplyContent(){
        return this.replyContent;
    }
    public void setReplyUserId(String replyUserId){
        this.replyUserId = replyUserId;
    }
    public String getReplyUserId(){
        return this.replyUserId;
    }
    public void setReplyDate(Date replyDate){
        this.replyDate = replyDate;
    }
    public Date getReplyDate(){
        return this.replyDate;
    }

}
