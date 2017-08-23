package cn.sparke.modules.goods.video.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.goods.video.constants.VideoConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.math.*;

/**
 * 视频Entity
 *
 * @author spark
 * @Date 2017-07-24 13:10:16
 */
public class VideoEntity extends BaseEntity{
    //网课ID
    private String networkCourseId;
    //课程的目录ID
    private String catalogId;
    //网课视频名称
    private String name;
    //类型 0: 录播; 1:直播
    private Integer type;
    //开始时间（直播时使用）
    private String startTime;
    //时长(0:12:56)
    private String duration;
    //大小（字节）（录播课时存在）
    private Integer size;
    //讲师id
    private String teacherId;
    //网课视频预告ccid
    private String previewCcId;
    //网课cc视频id(录播存在)
    private String videoCcId;
    //网课cc视频id(直播存在)
    private String liveCcId;
    //网课视频连接
    private String videoUrl;
    //网课cc视频直播间id
    private String liveRoomId;
    //封面图片
    private String coverImg;


    //非数据库字段
    private String videoStatus;
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getVideoStatus() {
        this.videoStatus=this.returnStr(this.getType(),this.getStartTime());
        return videoStatus;
    }

    public void setVideoStatus(String videoStatus) {
        this.videoStatus = videoStatus;
    }

    public String getLiveCcId() {
        return liveCcId;
    }

    public void setLiveCcId(String liveCcId) {
        this.liveCcId = liveCcId;
    }

    public void setNetworkCourseId(String networkCourseId){
        this.networkCourseId = networkCourseId;
    }
    public String getNetworkCourseId(){
        return this.networkCourseId;
    }
    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public String getDuration(){
        return this.duration;
    }
    public void setSize(Integer size){
        this.size = size;
    }
    public Integer getSize(){
        return this.size;
    }
    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }
    public String getTeacherId(){
        return this.teacherId;
    }
    public void setPreviewCcId(String previewCcId){
        this.previewCcId = previewCcId;
    }
    public String getPreviewCcId(){
        return this.previewCcId;
    }
    public void setVideoCcId(String videoCcId){
        this.videoCcId = videoCcId;
    }
    public String getVideoCcId(){
        return this.videoCcId;
    }
    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl(){
        return this.videoUrl;
    }
    public void setLiveRoomId(String liveRoomId){
        this.liveRoomId = liveRoomId;
    }
    public String getLiveRoomId(){
        return this.liveRoomId;
    }
    public void setCoverImg(String coverImg){
        this.coverImg = coverImg;
    }
    public String getCoverImg(){
        return this.coverImg;
    }

    private String returnStr(Integer type,String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (type == VideoConstants.RECORDED_VIDEO){
            return "看回放";
        }
        try {
            Date newdate=sdf.parse(date);
            if (date!=null){

                    Date dt1 = newdate;
                    Date dt2 = new Date();
                    if (dt1.getTime() < dt2.getTime()) {
                        return "已开始";
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return "未开始";
                    }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return "";
    }

}
