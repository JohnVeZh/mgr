package cn.sparke.modules.qrcode.QrCaptionListening.mapper;


import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;

import java.util.List;

/**
 * 二维码试卷字幕听力Dao
 *
 * @author spark
 * @Date 2017-07-26 23:27:40
 */
public interface QrCaptionListeningMapper extends BaseMapper<QrCaptionListeningEntity> {

    int insertList(List<QrCaptionListeningEntity> list);

    Integer deleteByQrcode(String qrCode);

}
