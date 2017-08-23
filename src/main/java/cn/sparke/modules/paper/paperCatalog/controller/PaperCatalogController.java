package cn.sparke.modules.paper.paperCatalog.controller;

import cn.sparke.core.common.constants.tips.Tip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.paper.paperCatalog.mapper.PaperCatalogMapper;
import cn.sparke.modules.paper.paperCatalog.service.PaperCatalogService;
import cn.sparke.modules.paper.paperCatalog.wrapper.PaperCatalogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 试卷目录控制器
 *
 * @author spark
 * @Date 2017-07-19 09:51:45
 */
@Controller
@RequestMapping("/paper/paperCatalog")
public class PaperCatalogController extends BaseController {

    private String PREFIX = "/paper/paperCatalog/";

    @Autowired
    private PaperCatalogService paperCatalogService;
    @Autowired
    private PaperCatalogMapper paperCatalogMapper;

    /**
     * 跳转到试卷目录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paperCatalog.html";
    }

    /**
     * 跳转到添加试卷目录
     */
    @RequestMapping("/paperCatalog_add")
    public String paperCatalogAdd() {
        return PREFIX + "paperCatalog_add.html";
    }

    /**
     * 跳转到修改试卷目录
     */
    @RequestMapping("/paperCatalog_update/{paperCatalogId}")
    public String paperCatalogUpdate(@PathVariable String paperCatalogId, Model model) {
       PaperCatalogEntity bean = paperCatalogService.getById(paperCatalogId);
        model.addAttribute("paperCatalog",bean);
        return PREFIX + "paperCatalog_edit.html";
    }

    /**
     * 获取试卷目录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperCatalogEntity entity) {
//        List<PaperCatalogEntity> list = paperCatalogService.findList(entity);
        List<PaperCatalogEntity> list = this.paperCatalogMapper.findList(entity);
        Object o = super.warpObject(new PaperCatalogWrapper(list));
        return o;
    }

    /**
     * 获取试卷目录列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectTreeList")
    @ResponseBody
    public List<ZTreeNode> selectTreeList(String type) {
        List<String> list = null;
        if (ToolUtil.isNotEmpty(type))
            list = Arrays.asList(type.split(","));
        List<ZTreeNode> roleTreeList = paperCatalogService.sectionTreeList(list);
        return roleTreeList;
    }

    /**
     * 获取试卷目录列表(试卷菜单查询用-树包含学段)
     */
    @RequestMapping(value = "/sectionTreeList")
    @ResponseBody
    public List<ZTreeNode> sectionTreeList() {
        List<ZTreeNode> roleTreeList = paperCatalogService.sectionTreeList(null);
        return roleTreeList;
    }

    /**
     * 获取试卷目录列表(试卷菜单查询用-树包含学段)
     */
    @RequestMapping(value = "/catalogTree")
    @ResponseBody
    public List<ZTreeNode> catalogTree(PaperCatalogEntity entity) {
        List<ZTreeNode> catalogTree = paperCatalogService.catalogTree(entity);
        return catalogTree;
    }

    /**
     * 新增试卷目录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid PaperCatalogEntity entity) {
        setParent(entity);
        entity.preInsert();
        paperCatalogService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除试卷目录检查
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public String check(String id) {
        return paperCatalogService.check(id);
    }

    /**
     * 删除试卷目录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        paperCatalogService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改试卷目录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperCatalogEntity entity) {
        setParent(entity);
        paperCatalogService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 试卷目录详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return paperCatalogService.getById(id);
    }

    /**
     * 根据请求的父级编号设置层级
     */
    private void setParent(@Valid PaperCatalogEntity entity) {
        if (ToolUtil.isEmpty(entity.getParentId()) || entity.getParentId().equals("0")) {
            entity.setParentId("0");
            entity.setLevel(1);
        } else {
            PaperCatalogEntity paperCatalogEntity = paperCatalogService.getById(entity.getParentId());
            Integer pLevels = paperCatalogEntity.getLevel();
            entity.setLevel(pLevels + 1);
            entity.setParentId(paperCatalogEntity.getId());
        }
    }


}
