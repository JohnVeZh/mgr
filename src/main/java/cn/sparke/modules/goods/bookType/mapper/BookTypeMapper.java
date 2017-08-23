package cn.sparke.modules.goods.bookType.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.bookType.entity.BookTypeEntity;

import java.util.List;


/**
 * 图书类型Dao
 *
 * @author spark
 * @Date 2017-07-19 16:14:23
 */
public interface BookTypeMapper extends BaseMapper<BookTypeEntity>{
    List<ZTreeNode> tree();

}
