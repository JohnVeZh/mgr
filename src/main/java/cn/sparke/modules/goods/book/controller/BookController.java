package cn.sparke.modules.goods.book.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.book.entity.BookEntity;
import cn.sparke.modules.goods.book.service.BookService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * 图书控制器
 *
 * @author spark
 * @Date 2017-07-19 16:05:35
 */
@Controller
@RequestMapping("/goods/book")
public class BookController extends BaseController {

    private String PREFIX = "/goods/book/";

     @Autowired
     private BookService bookService;

    /**
     * 跳转到图书首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "book.html";
    }

    /**
     * 跳转到添加图书
     */
    @RequestMapping("/book_add")
    public String bookAdd() {
        return PREFIX + "book_add.html";
    }

    /**
     * 跳转到修改图书
     */
    @RequestMapping("/book_update/{bookId}")
    public String bookUpdate(@PathVariable String bookId, Model model) {
       BookEntity bean = bookService.getById(bookId);
        model.addAttribute("book",bean);
        return PREFIX + "book_edit.html";
    }
    /**
     * 跳转到修改图书
     */
    @RequestMapping("/book_updateIsShow/{bookId}")
    public String book_updateIsShow(@PathVariable String bookId, Model model) {
       BookEntity bean = bookService.getById(bookId);
        model.addAttribute("book",bean);
        return PREFIX + "book_isShow.html";
    }

    /**
     * 获取图书列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(BookEntity entity) {
        Page<BookEntity> bookEntities = bookService.queryAllList(entity);
        return new PageInfo<>(bookEntities);
    }

    /**
     * 新增图书
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated BookEntity entity) {
        bookService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除图书
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        bookService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改图书
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated BookEntity entity) {
        bookService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 图书详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return bookService.getById(id);
    }
}
