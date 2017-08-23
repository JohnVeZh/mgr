package cn.sparke.modules.business.version.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.business.version.service.VersionService;
import cn.sparke.modules.business.version.entity.VersionEntity;

/**
 * 版本控制器
 *
 * @author spark
 * @Date 2017-07-26 10:08:44
 */
@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {

    private String PREFIX = "/business/version/";

     @Autowired
     private VersionService versionService;

    /**
     * 跳转到版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "version.html";
    }

    /**
     * 跳转到添加版本
     */
    @RequestMapping("/version_add")
    public String versionAdd() {
        return PREFIX + "version_add.html";
    }

    /**
     * 跳转到修改版本
     */
    @RequestMapping("/version_update/{versionId}")
    public String versionUpdate(@PathVariable String versionId, Model model) {
       VersionEntity bean = versionService.getById(versionId);
        model.addAttribute("version",bean);
        return PREFIX + "version_edit.html";
    }

    /**
     * 获取版本列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(VersionEntity entity) {
        Page page = versionService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated VersionEntity entity) {
        versionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String versionId) {
        versionService.deleteById(versionId);
        return SUCCESS_TIP;
    }


    /**
     * 修改版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated VersionEntity entity) {
        versionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 版本详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return versionService.getById(id);
    }
}
