package cn.sparke.modules.system.code.service;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.core.common.support.StrKit;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.system.code.template.config.ContextConfig;
import cn.sparke.modules.system.code.template.engine.SimpleTemplateEngine;
import cn.sparke.modules.system.code.template.engine.base.SparkTemplateEngine;
import cn.sparke.modules.system.code.bean.TableColumnBean;
import cn.sparke.modules.system.code.mapper.TableColumnMapper;
import cn.sparke.modules.system.code.utils.ColumnConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangbowen on 2017/7/18.
 */
@Service
public class CodeService {
    @Autowired
    private TableColumnMapper tableColumnMapper;

    /**
     * 生成代码
     *
     * @param contextConfig
     */
    public void generate(ContextConfig contextConfig) {
        if (ToolUtil.isEmpty(contextConfig.getTableName())) {
            contextConfig.setEntitySwitch(false);
            contextConfig.setMapperSwitch(false);
        } else {
            //处理要生成的表
            processTable(contextConfig);
        }
        SparkTemplateEngine sparkTemplateEngine = new SimpleTemplateEngine();
        sparkTemplateEngine.setContextConfig(contextConfig);
        sparkTemplateEngine.start();
    }

    private void processTable(ContextConfig contextConfig) {
        String tableName = contextConfig.getTableName();
        TableColumnBean query = new TableColumnBean();
        query.setName(tableName);
        List<TableColumnBean> columnList = tableColumnMapper.findList(query);
        if (columnList.size() == 0) {
            return;
        }
        contextConfig.setHasProperties(true);
        contextConfig.setTablePropertyList(columnList);
        //转换数据库字段为java字段
        columnList.forEach(tableColumnBean -> {
            tableColumnBean.setJavaName(StrKit.toCamelCase(tableColumnBean.getName()));
            tableColumnBean.setJavaType(ColumnConvert.jdbcToJavaType(tableColumnBean.getJdbcType()));
        });
        //剔除baseEntity中存在的实体
        Field[] baseFieldAttr = BaseEntity.class.getDeclaredFields();
        List<String> baseFieldList = Arrays.stream(baseFieldAttr).map(Field::getName).collect(Collectors.toList());
        List<TableColumnBean> entityPropertyList = columnList.stream().filter(javaPropertyBean -> !baseFieldList.contains(javaPropertyBean.getJavaName())).collect(Collectors.toList());
        contextConfig.setEntityPropertyList(entityPropertyList);
    }
}
