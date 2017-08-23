package cn.sparke.modules.goods.video.service;

import cn.sparke.modules.goods.video.entity.VideoEntity;
import cn.sparke.modules.goods.video.mapper.VideoMapper;
import cn.sparke.modules.orders.order.entity.ExcelOrderBookEntity;
import cn.sparke.modules.orders.order.entity.ExcelOrderCourseEntity;
import cn.sparke.modules.orders.utils.DateUtils;
import cn.sparke.modules.orders.utils.ExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 视频Dao
 *
 * @author spark
 * @Date 2017-07-24 13:10:16
 */
@Service
public class VideoService{
    @Autowired
    private VideoMapper videoMapper;

    public void save(VideoEntity video){
        video.preInsert();
        videoMapper.insert(video);
    }

    public void update(VideoEntity video){
        video.preUpdate();
        videoMapper.update(video);
    }

    public VideoEntity get(VideoEntity video){
        return videoMapper.get(video);
    }

    public VideoEntity getById(String id){
        return videoMapper.getById(id);
    }
    public Page<VideoEntity> findList(VideoEntity video){
       return videoMapper.findList(video);
    }

    public void deleteById(String id){
      videoMapper.deleteById(id);
    }



}
