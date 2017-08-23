package cn.sparke.modules.system.dept.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.system.dept.entity.Dept;

import java.util.List;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public interface DeptMapper extends BaseMapper<Dept> {
    List<ZTreeNode> tree();

}
