package cn.sparke.modules.popUp.controller;

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
import cn.sparke.modules.popUp.service.PopUpService;
import cn.sparke.modules.popUp.entity.PopUpEntity;

/**
 * 弹窗控制器
 *
 * @author spark
 * @Date 2017-08-19 14:02:51
 */
@Controller
@RequestMapping("/popUp")
public class PopUpController extends BaseController {

    private String PREFIX = "/popUp/";

     @Autowired
     private PopUpService popUpService;

    /**
     * 跳转到弹窗首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "popUp.html";
    }

    /**
     * 跳转到添加弹窗
     */
    @RequestMapping("/popUp_add")
    public String popUpAdd() {
        return PREFIX + "popUp_add.html";
    }

    /**
     * 跳转到修改弹窗
     */
    @RequestMapping("/popUp_update/{popUpId}")
    public String popUpUpdate(@PathVariable String popUpId, Model model) {
       PopUpEntity bean = popUpService.getById(popUpId);
        model.addAttribute("popUp",bean);
        return PREFIX + "popUp_edit.html";
    }

    /**
     * 获取弹窗列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PopUpEntity entity) {
        Page<PopUpEntity> list = popUpService.findList(entity);
        return new PageInfo<>(list, list.getTotal());
    }

    /**
     * 新增弹窗
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PopUpEntity entity) {
        return popUpService.save(entity);
    }

    /**
     * 删除弹窗
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String popUpId) {
        popUpService.deleteById(popUpId);
        return SUCCESS_TIP;
    }


    /**
     * 修改弹窗
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PopUpEntity entity) {
        popUpService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 弹窗详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return popUpService.getById(id);
    }
    @RequestMapping(value = "/img")
    public Object imgDetail(String imgSrc,Model model){
        model.addAttribute("img",imgSrc);
        return PREFIX+"popUp_img.html";
    }
}
