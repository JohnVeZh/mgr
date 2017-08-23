package cn.sparke.modules.goods.bookType.service;

import cn.sparke.modules.goods.bookType.entity.BookTypeEntity;
import cn.sparke.modules.goods.bookType.mapper.BookTypeMapper;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 图书类型Dao
 *
 * @author spark
 * @Date 2017-07-19 16:14:23
 */
@Service
public class BookTypeService{
    @Autowired
    private BookTypeMapper bookTypeMapper;

    public void save(BookTypeEntity bookType){
        bookType.preInsert();
        //如果不选，默认为顶级
        if(bookType!=null && StringUtils.isEmpty(bookType.getParentId())){
            bookType.setParentId("FFFFFF");
        }
        bookTypeMapper.insert(bookType);
    }

    public void update(BookTypeEntity bookType){
        bookType.preUpdate();
        bookTypeMapper.update(bookType);
    }

    public BookTypeEntity get(BookTypeEntity bookType){
        return bookTypeMapper.get(bookType);
    }

    public BookTypeEntity getById(String id){
        return bookTypeMapper.getById(id);
    }
    public Page<BookTypeEntity> findList(BookTypeEntity bookType){
       return bookTypeMapper.findList(bookType);
    }

    public void deleteById(String id){
        BookTypeEntity bean = new BookTypeEntity();
        bean.setParentId(id);
        List<BookTypeEntity> list = bookTypeMapper.findList(bean);
        for (BookTypeEntity entity : list){
            bookTypeMapper.deleteById(entity.getId());
        }
        bookTypeMapper.deleteById(id);
    }

}
