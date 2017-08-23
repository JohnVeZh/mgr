package cn.sparke.modules.goods.book.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.book.entity.BookEntity;
import com.github.pagehelper.Page;

import java.util.List;


/**
 * 图书Dao
 *
 * @author spark
 * @Date 2017-07-19 16:05:35
 */
public interface BookMapper extends BaseMapper<BookEntity>{

    Page<BookEntity> queryAllList(BookEntity bookEntity);



}
