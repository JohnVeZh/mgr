package cn.sparke.modules.gift.activationCode.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.gift.activationCode.entity.ActivationCodeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 激活码管理Dao
 *
 * @author spark
 * @Date 2017-08-22 09:48:21
 */
public interface ActivationCodeMapper extends BaseMapper<ActivationCodeEntity>{


    int batchInsert(@Param("sectionCode") int sectionCode, @Param("codeList") List<String> codeList);

}
