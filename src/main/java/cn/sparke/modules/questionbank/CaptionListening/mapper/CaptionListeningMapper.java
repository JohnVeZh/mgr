package cn.sparke.modules.questionbank.CaptionListening.mapper;


import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.questionbank.CaptionListening.entity.CaptionListeningEntity;
import com.github.pagehelper.Page;
import javafx.print.Paper;

import java.util.List;
import java.util.Map;

/**
 * 字幕听力音频Dao
 *
 * @author spark
 * @Date 2017-07-26 23:01:00
 */
public interface CaptionListeningMapper extends BaseMapper<CaptionListeningEntity> {

    int insertList(List<CaptionListeningEntity> list);
//    CaptionListeningEntity getByIds(Map<String,Object> dataMap);
//    Page<CaptionListeningEntity> findQrList(CaptionListeningEntity entity);
//    CaptionListeningEntity getQrByIds(Map<String,Object> dataMap);
}
