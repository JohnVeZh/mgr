package cn.sparke.modules.questionbank.CaptionListeningVideo.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.CaptionListeningVideo.entity.CaptionListeningVideoEntity;

import java.util.List;

/**
 * 字幕听力视频Dao
 *
 * @author spark
 * @Date 2017-07-26 22:58:39
 */
public interface CaptionListeningVideoMapper extends BaseMapper<CaptionListeningVideoEntity>{

    int insertList(List<CaptionListeningVideoEntity> list);


}
