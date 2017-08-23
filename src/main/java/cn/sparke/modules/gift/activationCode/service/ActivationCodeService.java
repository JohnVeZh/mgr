package cn.sparke.modules.gift.activationCode.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.gift.activationCode.mapper.ActivationCodeMapper;
import cn.sparke.modules.gift.activationCode.entity.ActivationCodeEntity;

import java.util.List;

/**
 * 激活码管理Dao
 *
 * @author spark
 * @Date 2017-08-22 09:48:21
 */
@Service
public class ActivationCodeService{
    @Autowired
    private ActivationCodeMapper activationCodeMapper;

    public int batchInsert(int sectionCode, List<String> codeList) {
        return activationCodeMapper.batchInsert(sectionCode, codeList);
    }

    public Page<ActivationCodeEntity> findList(ActivationCodeEntity activationCode){
       return activationCodeMapper.findList(activationCode);
    }


}
