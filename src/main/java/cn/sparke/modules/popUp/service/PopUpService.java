package cn.sparke.modules.popUp.service;

import cn.sparke.core.common.constants.tips.ErrorTip;
import cn.sparke.core.common.constants.tips.SuccessTip;
import cn.sparke.core.common.constants.tips.Tip;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.popUp.mapper.PopUpMapper;
import cn.sparke.modules.popUp.entity.PopUpEntity;

/**
 * 弹窗Dao
 *
 * @author spark
 * @Date 2017-08-19 14:02:51
 */
@Service
public class PopUpService {
    @Autowired
    private PopUpMapper popUpMapper;

    public Tip save(PopUpEntity popUp) {
        Integer count = popUpMapper.selectCount(popUp);
        if (count != 0) {
            return new ErrorTip(500, "添加失败！在指定时间区间内该展示模块已设置弹框，请勿重复添加");
        }
        popUp.preInsert();
        popUpMapper.insert(popUp);
        return new SuccessTip();
    }

    public void update(PopUpEntity popUp) {
        popUp.preUpdate();
        popUpMapper.update(popUp);
    }

    public PopUpEntity get(PopUpEntity popUp) {
        return popUpMapper.get(popUp);
    }

    public PopUpEntity getById(String id) {
        return popUpMapper.getById(id);
    }

    public Page<PopUpEntity> findList(PopUpEntity popUp) {
        return popUpMapper.findList(popUp);
    }

    public void deleteById(String id) {
        popUpMapper.deleteById(id);
    }

}
