package cn.sparke.modules.goods.book.service;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.goods.book.constants.ProductConstants;
import cn.sparke.modules.goods.book.entity.BookEntity;
import cn.sparke.modules.goods.book.mapper.BookMapper;
import cn.sparke.modules.goods.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 图书Dao
 *
 * @author spark
 * @Date 2017-07-19 16:05:35
 */
@Service
public class BookService{
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ProductMapper productMapper;
    @Transactional
    public void save(BookEntity book){
        book.preInsert();
        //选择图书类型
        book.setType(ProductConstants.PRODUCT_BOOK_TYPE);
        productMapper.insert(book);
        //插入productId和book的Id
        book.setProductId(book.getId());
        book.setId(ToolUtil.uuid());
        bookMapper.insert(book);
    }

    @Transactional
    public void update(BookEntity book){
        book.preUpdate();
        productMapper.update(book);

        bookMapper.update(book);
    }

    public BookEntity get(BookEntity book){
        return bookMapper.get(book);
    }

    public BookEntity getById(String id){
        return bookMapper.getById(id);
    }
    public Page<BookEntity> findList(BookEntity book){
       return bookMapper.findList(book);
    }
    public Page<BookEntity> queryAllList(BookEntity book){
       return bookMapper.queryAllList(book);
    }

    public void deleteById(String id){
        productMapper.deleteById(id);
    }

}
