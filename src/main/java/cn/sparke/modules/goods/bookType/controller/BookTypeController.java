package cn.sparke.modules.goods.bookType.controller;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.modules.goods.bookType.entity.BookTypeEntity;
import cn.sparke.modules.goods.bookType.mapper.BookTypeMapper;
import cn.sparke.modules.goods.bookType.service.BookTypeService;
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
 * 图书类型控制器
 *
 * @author spark
 * @Date 2017-07-19 16:14:23
 */
@Controller
@RequestMapping("/goods/bookType")
public class BookTypeController extends BaseController {

    private String PREFIX = "/goods/bookType/";

    @Autowired
    private BookTypeMapper bookTypeMapper;

     @Autowired
     private BookTypeService bookTypeService;

    /**
     * 跳转到图书类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bookType.html";
    }

    /**
     * 跳转到添加图书类型
     */
    @RequestMapping("/bookType_add")
    public String bookTypeAdd() {
        return PREFIX + "bookType_add.html";
    }

    /**
     * 跳转到修改图书类型
     */
    @RequestMapping("/bookType_update/{bookTypeId}")
    public String bookTypeUpdate(@PathVariable String bookTypeId, Model model) {
       BookTypeEntity bookType = bookTypeService.getById(bookTypeId);
        model.addAttribute("bookType",bookType);
        return PREFIX + "bookType_edit.html";
    }

    /**
     * 获取图书类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(BookTypeEntity entity) {
        Page<BookTypeEntity> list = bookTypeService.findList(entity);
        return list;
    }

    /**
     * 新增图书类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated BookTypeEntity entity) {
        bookTypeService.save(entity);
        return super.SUCCESS_TIP;
    }


    /**
     * 获取类型的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree =this.bookTypeMapper.tree();
        tree.add(ZTreeNode.createBookTypeParent());
        return tree;
    }



    /**
     * 删除图书类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String bookTypeId) {
        bookTypeService.deleteById(bookTypeId);
        return SUCCESS_TIP;
    }


    /**
     * 修改图书类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated BookTypeEntity entity) {
        bookTypeService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 图书类型详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return bookTypeService.getById(id);
    }
}
