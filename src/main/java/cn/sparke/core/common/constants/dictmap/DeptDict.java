package cn.sparke.core.common.constants.dictmap;


import cn.sparke.core.common.constants.dictmap.base.AbstractDictMap;

/**
 * 部门的映射
 *
 * @author spark
 * @date 2017-05-06 15:01
 */
public class DeptDict extends AbstractDictMap {

    @Override
    public void init() {
        put("deptId", "部门名称");
        put("num", "部门排序");
        put("pid", "上级名称");
        put("simplename", "部门简称");
        put("fullname", "部门全称");
        put("tips", "备注");
    }

    @Override
    protected void initBeWrapped() {
//        putFieldWrapperMethodName("deptId", "getDeptName");
//        putFieldWrapperMethodName("pid", "getDeptName");
    }
}
