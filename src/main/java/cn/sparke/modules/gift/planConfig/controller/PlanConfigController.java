package cn.sparke.modules.gift.planConfig.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.gift.planConfig.entity.PlanConfigEntity;
import cn.sparke.modules.gift.planConfig.service.PlanConfigService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 学习方案控制器
 *
 * @author spark
 * @Date 2017-08-19 10:33:52
 */
@Controller
@RequestMapping("/gift/planConfig")
public class PlanConfigController extends BaseController {

    private String PREFIX = "/gift/planConfig/";

     @Autowired
     private PlanConfigService planConfigService;

    /**
     * 跳转到学习方案首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "planConfig.html";
    }

    /**
     * 跳转到添加学习方案
     */
    @RequestMapping("/planConfig_add")
    public String planConfigAdd() {
        return PREFIX + "planConfig_add.html";
    }

    /**
     * 跳转到修改学习方案
     */
    @RequestMapping("/planConfig_update/{planConfigId}")
    public String planConfigUpdate(@PathVariable String planConfigId, Model model) {
       PlanConfigEntity bean = planConfigService.getById(planConfigId);
        model.addAttribute("planConfig",bean);
        return PREFIX + "planConfig_edit.html";
    }

    /**
     * 获取学习方案列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PlanConfigEntity entity) {
        Page<PlanConfigEntity> list = planConfigService.findList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 新增学习方案
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PlanConfigEntity entity) {
        planConfigService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除学习方案
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String planConfigId) {
        planConfigService.deleteById(planConfigId);
        return SUCCESS_TIP;
    }


    /**
     * 修改学习方案
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PlanConfigEntity entity) {
        planConfigService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 学习方案详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return planConfigService.getById(id);
    }
}
