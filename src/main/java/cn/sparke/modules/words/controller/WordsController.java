package cn.sparke.modules.words.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.product.entity.ProductEntity;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.words.service.WordsService;
import cn.sparke.modules.words.entity.WordsEntity;

import java.util.List;

/**
 * 词汇管理控制器
 *
 * @author spark
 * @Date 2017-07-25 20:49:20
 */
@Controller
@RequestMapping("/words")
public class WordsController extends BaseController {

    private String PREFIX = "/words/";

     @Autowired
     private WordsService wordsService;

    /**
     * 跳转到词汇管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "words.html";
    }

    /**
     * 跳转到添加词汇管理
     */
    @RequestMapping("/words_add")
    public String wordsAdd() {
        return PREFIX + "words_add.html";
    }

    /**
     * 跳转到修改词汇管理
     */
    @RequestMapping("/words_update/{wordsId}")
    public String wordsUpdate(@PathVariable String wordsId, Model model) {
       WordsEntity bean = wordsService.getById(wordsId);
        model.addAttribute("words",bean);
        return PREFIX + "words_edit.html";
    }

    /**
     * 获取词汇管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(WordsEntity entity) {
        String nameLike = entity.getNameLike();
        if (!Strings.isNullOrEmpty(nameLike)){
            entity.setName(nameLike);
        }
        Page page =  wordsService.findList(entity);
        return new PageInfo<>((List<ProductEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增词汇管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated WordsEntity entity) {
        wordsService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除词汇管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String wordsId) {
        wordsService.deleteById(wordsId);
        return SUCCESS_TIP;
    }


    /**
     * 修改词汇管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated WordsEntity entity) {
        wordsService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 词汇管理详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return wordsService.getById(id);
    }
}
