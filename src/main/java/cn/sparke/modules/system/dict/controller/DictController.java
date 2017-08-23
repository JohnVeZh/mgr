package cn.sparke.modules.system.dict.controller;

import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.constants.dictmap.DictMap;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.dict.entity.DictEntity;
import cn.sparke.modules.system.dict.mapper.DictMapper;
import cn.sparke.modules.system.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 字典控制器
 *
 * @author spark
 * @Date 2017年4月26日 12:55:31
 */
@Controller
@RequestMapping("/system/dict")
public class DictController extends BaseController {

    private String PREFIX = "/system/dict/";

    @Autowired
    private DictMapper dictMapper;


    @Autowired
    private DictService dictService;

    /**
     * 跳转到字典管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 跳转到添加字典
     */
    @RequestMapping("/dict_add")
    public String deptAdd() {
        return PREFIX + "dict_add.html";
    }

    /**
     * 跳转到修改字典
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping("/dict_edit/{dictId}")
    public String deptUpdate(@PathVariable String dictId, Model model) {
        DictEntity dict = dictMapper.getById(dictId);
        model.addAttribute("dict", dict);
        model.addAttribute("subDicts", dict.getChildren());
        LogObjectHolder.me().set(dict);
        return PREFIX + "dict_edit.html";
    }

    /**
     * 新增字典
     */
    @BussinessLog(value = "添加字典记录", key = "dictName,dictValues", dict =  DictMap.class)
    @RequestMapping(value = "/add")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object add(@RequestBody @Validated DictEntity dictEntity) {
        this.dictService.addDict(dictEntity);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有字典列表，分页
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(DictEntity dictEntity) {
        return new PageInfo<>(this.dictMapper.findList(dictEntity));
    }

    /**
     * 字典详情
     */
    @RequestMapping(value = "/detail/{dictId}")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable("dictId") String dictId) {
        return dictMapper.getById(dictId);
    }

    /**
     * 修改字典
     */
    @BussinessLog(value = "修改字典", key = "value,label,type,description", dict = DictMap.class)
    @RequestMapping(value = "/update")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object update(@RequestBody @Validated DictEntity dictEntity) {
        dictService.editDict(dictEntity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除字典记录
     */
    @BussinessLog(value = "删除字典记录", key = "dictId", dict =  DictMap.class)
    @RequestMapping(value = "/delete")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delete(@RequestParam String dictId) {
        DictEntity dictEntity = dictMapper.getById(dictId);
        //缓存被删除的名称
        LogObjectHolder.me().set(dictEntity.getDescription());
        dictService.delteDict(dictId);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到启动页
     */
    @RequestMapping("/start")
    public String start(Model model) {
        DictEntity dict = new DictEntity();
        dict.setType("start_img");
        dict = dictMapper.get(dict);
        model.addAttribute("dict", dict);
        LogObjectHolder.me().set(dict);
        return PREFIX + "start.html";
    }
    /**
     * 跳转到网站首页背景图
     */
    @RequestMapping("/pcIndexBackgroundImg")
    public String background(Model model) {
        DictEntity dict = new DictEntity();
        dict.setType("pc_index_background_img");
        dict = dictMapper.get(dict);
        model.addAttribute("dict", dict);
        LogObjectHolder.me().set(dict);
        return PREFIX + "pcBackground.html";
    }
}
