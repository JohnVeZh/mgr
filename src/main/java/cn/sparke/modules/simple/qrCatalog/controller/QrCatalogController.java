package cn.sparke.modules.simple.qrCatalog.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.simple.qrCatalog.entity.QrCatalogEntity;
import cn.sparke.modules.simple.qrCatalog.service.QrCatalogService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 简系列目录控制器
 *
 * @author spark
 * @Date 2017-08-09 10:23:51
 */
@Controller
@RequestMapping("/simple/qrCatalog")
public class QrCatalogController extends BaseController {

    private String PREFIX = "/simple/qrCatalog/";

     @Autowired
     private QrCatalogService qrCatalogService;

    /**
     * 跳转到简系列目录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrCatalog.html";
    }

    /**
     * 跳转到添加简系列目录
     */
    @RequestMapping("/qrCatalog_add")
    public String qrCatalogAdd() {
        return PREFIX + "qrCatalog_add.html";
    }

    /**
     * 跳转到修改简系列目录
     */
    @RequestMapping("/qrCatalog_update/{qrCatalogId}")
    public String qrCatalogUpdate(@PathVariable String qrCatalogId, Model model) {
       QrCatalogEntity bean = qrCatalogService.getById(qrCatalogId);
        model.addAttribute("qrCatalog",bean);
        return PREFIX + "qrCatalog_edit.html";
    }

    /**
     * 获取简系列目录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QrCatalogEntity entity) {
        entity.setName(entity.getTitle());
        Page page =  qrCatalogService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增简系列目录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QrCatalogEntity entity) {
        qrCatalogService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除简系列目录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String qrCatalogId) {
        qrCatalogService.deleteById(qrCatalogId);
        return SUCCESS_TIP;
    }


    /**
     * 修改简系列目录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrCatalogEntity entity) {
        qrCatalogService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 简系列目录详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return qrCatalogService.getById(id);
    }
}
