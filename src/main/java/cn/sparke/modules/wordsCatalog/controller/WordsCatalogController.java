package cn.sparke.modules.wordsCatalog.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.community.comment.entity.vo.UserCommentVoEntity;
import cn.sparke.modules.goods.bookType.entity.BookTypeEntity;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import cn.sparke.modules.system.menu.wrapper.MenuWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.wordsCatalog.service.WordsCatalogService;
import cn.sparke.modules.wordsCatalog.entity.WordsCatalogEntity;

import java.util.List;

/**
 * 词汇栏目管理控制器
 *
 * @author spark
 * @Date 2017-07-25 19:51:11
 */
@Controller
@RequestMapping("/wordsCatalog")
public class WordsCatalogController extends BaseController {

    private String PREFIX = "/wordsCatalog/";

    @Autowired
    private WordsCatalogService wordsCatalogService;

    /**
     * 跳转到词汇栏目管理首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "isSelect", defaultValue = "false") boolean isSelect, Model model) {
        model.addAttribute("isSelect", isSelect);
        return PREFIX + "wordsCatalog.html";
    }

    /**
     * 跳转到添加词汇栏目管理
     */
    @RequestMapping("/wordsCatalog_add_root")
    public String wordsCatalogAddRoot() {
        return PREFIX + "wordsCatalog_add_root.html";
    }

    /**
     * 跳转到添加词汇栏目管理
     */
    @RequestMapping("/wordsCatalog_add")
    public String wordsCatalogAdd() {
        return PREFIX + "wordsCatalog_add.html";
    }


    /**
     * 跳转到修改词汇栏目管理
     */
    @RequestMapping("/wordsCatalog_update/{wordsCatalogId}")
    public String wordsCatalogUpdate(@PathVariable String wordsCatalogId, Model model) {
        WordsCatalogEntity bean = wordsCatalogService.getById(wordsCatalogId);
        model.addAttribute("wordsCatalog", bean);
        return PREFIX + "wordsCatalog_edit.html";
    }

    /**
     * 获取词汇栏目管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(WordsCatalogEntity entity) {
        Page<WordsCatalogEntity> list  = wordsCatalogService.findList(entity);
        return list;
         //new PageInfo<>((List<WordsCatalogEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增词汇栏目管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated WordsCatalogEntity entity) {
        wordsCatalogService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除词汇栏目管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String wordsCatalogId) {
        wordsCatalogService.deleteById(wordsCatalogId);
        return SUCCESS_TIP;
    }


    /**
     * 修改词汇栏目管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated WordsCatalogEntity entity) {
        wordsCatalogService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 词汇栏目管理详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return wordsCatalogService.getById(id);
    }
}
