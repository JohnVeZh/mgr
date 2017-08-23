package cn.sparke.modules.simple.qrCaptionListening.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.simple.qrCaptionListening.entity.SimpleCaptionListeningEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SimpleCaptionListeningMapper  extends  BaseMapper<SimpleCaptionListeningEntity>{

}
