package cn.sparke.modules.system.dict.utils;


import com.alibaba.fastjson.TypeReference;
import cn.sparke.core.common.config.properties.ProjectProperties;
import cn.sparke.core.modules.cache.CacheFacade;
import cn.sparke.modules.system.dict.entity.DictEntity;
import cn.sparke.modules.system.dict.mapper.DictMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * <p/>
 * Created by zhangbowen on 2015/12/22.
 */
@Configuration
public class DictUtils {
    public static final String CACHE_DICT_MAP = "dictMap";
    private static DictMapper dictMapper;
    private static DictUtils dictUtils;
    private static ProjectProperties projectProperties;

    @Bean
    public DictUtils init(DictMapper dict, ProjectProperties constants) {
        dictMapper = dict;
        projectProperties = constants;
        if (dictUtils == null) {
            dictUtils = new DictUtils();
        }
        return dictUtils;
    }

    /**
     * 类型，值查询标题
     *
     * @param value        值
     * @param type         类型
     * @param defaultValue 默认值
     */
    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (DictEntity dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    /**
     * 类型，值查询标题
     *
     * @param value 值
     * @param type  类型
     */
    public static String getDictLabel(Object value, String type) {
        return getDictLabel(value + "", type, "");
    }

    /**
     * 标题，类型查询值
     *
     * @param label        标题
     * @param type         类型
     * @param defaultLabel
     */
    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (DictEntity dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    /**
     * 标题，类型查询值
     *
     * @param label 标题
     * @param type  类型
     */
    public static DictEntity getDict(String label, String type) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (DictEntity dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict;
                }
            }
        }
        return null;
    }

    private static List<DictEntity> findAllList() {
        return dictMapper.findAllList();
    }

    /**
     * 类型查询字典列表
     *
     * @param type 类型
     */
    public static List<DictEntity> getDictList(String type) {
        Map<String, List<DictEntity>> dictMap = null;
        if (!"local".equals(projectProperties.cache)) {
            dictMap = CacheFacade.getObject(CACHE_DICT_MAP, new TypeReference<Map<String, List<DictEntity>>>() {
            });
        }
        if (dictMap == null) {
            dictMap = new HashMap<>();
            List<DictEntity> allList = dictMapper.findAllList();
            for (DictEntity dict : allList) {
                List<DictEntity> dictList = dictMap.get(dict.getType());
                if (dictList != null) {
                    dictList.add(dict);
                } else {
                    List<DictEntity> list = new ArrayList<>();
                    list.add(dict);
                    dictMap.put(dict.getType(), list);
                }
            }
            CacheFacade.set(CACHE_DICT_MAP, dictMap, 0);
        }
        List<DictEntity> dictList = dictMap.get(type);
        if (dictList == null) {
            dictList = new ArrayList<>();
        }
        return dictList;
    }

    /**
     * 获取lables
     *
     * @param type
     */
    public static List<String> getDicLabelList(String type) {
        List<DictEntity> dictList = DictUtils.getDictList(type);
        List<String> dictStrList = new ArrayList<String>();
        for (DictEntity dict : dictList) {
            dictStrList.add(dict.getLabel());
        }
        return dictStrList;
    }

}
