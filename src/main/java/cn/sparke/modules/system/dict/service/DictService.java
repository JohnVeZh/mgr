package cn.sparke.modules.system.dict.service;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.cache.CacheFacade;
import cn.sparke.modules.system.dict.entity.DictEntity;
import cn.sparke.modules.system.dict.mapper.DictMapper;
import cn.sparke.modules.system.dict.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DictService {

    @Autowired
    private DictMapper dictMapper;

    public void addDict(DictEntity dictEntity) {
        //判断有没有该字典
        List<DictEntity> dicts = dictMapper.existDict(dictEntity);
        if (dicts != null && dicts.size() > 0) {
            throw new BussinessException(BizExceptionEnum.DICT_EXISTED);
        }
        //插入字典
        dictEntity.setParentId("0");
        dictEntity.preInsert();
        if (dictEntity.getSort() == null) {
            dictEntity.setSort(0);
        }
        dictMapper.insert(dictEntity);
        List<DictEntity> children = dictEntity.getChildren();
        if (ToolUtil.isNotEmpty(children)) {
            //插入子字典
            for (int i = 0; i < children.size(); i++) {
                DictEntity dict = children.get(i);
                dict.setType(dictEntity.getType());
                dict.setParentId(dictEntity.getId());
                dict.setSort(i + 1);
                dict.preInsert();
                dictMapper.insert(dict);
            }
        }
        CacheFacade.delete(DictUtils.CACHE_DICT_MAP);
    }

    public void editDict(DictEntity dictEntity) {
        //删除之前的字典
        this.delteRealDict(dictEntity.getId());
        //重新添加新的字典
        this.addDict(dictEntity);
        CacheFacade.delete(DictUtils.CACHE_DICT_MAP);
    }

    public void delteRealDict(String dictId) {
        DictEntity dictEntity = dictMapper.getById(dictId);
        if (dictEntity == null) {
            return;
        }
        //删除这个词典
        dictMapper.deleteRealById(dictId);
        if (ToolUtil.isNotEmpty(dictEntity.getChildren())) {
            dictEntity.getChildren().forEach(dict -> {
                dictMapper.deleteRealById(dict.getId());
            });
        }
    }

    public void delteDict(String dictId) {
        DictEntity dictEntity = dictMapper.getById(dictId);
        if (dictEntity == null) {
            return;
        }
        //删除这个词典
        dictMapper.deleteById(dictId);
        if (ToolUtil.isNotEmpty(dictEntity.getChildren())) {
            dictEntity.getChildren().forEach(dict -> {
                dictMapper.deleteById(dict.getId());
            });
        }
        CacheFacade.delete(DictUtils.CACHE_DICT_MAP);
    }
}
