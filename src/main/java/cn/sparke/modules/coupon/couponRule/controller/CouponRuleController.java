package cn.sparke.modules.coupon.couponRule.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.coupon.couponRule.service.CouponRuleService;
import cn.sparke.modules.coupon.couponRule.entity.CouponRuleEntity;

import java.util.List;

/**
 * 优惠劵规则控制器
 *
 * @author spark
 * @Date 2017-07-26 14:21:45
 */
@Controller
@RequestMapping("/couponRule")
public class CouponRuleController extends BaseController {

    private String PREFIX = "/couponRule/";

     @Autowired
     private CouponRuleService couponRuleService;

    /**
     * 跳转到优惠劵规则首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "couponRule.html";
    }

    /**
     * 跳转到添加优惠劵规则
     */
    @RequestMapping("/couponRule_add")
    public String couponRuleAdd() {
        return PREFIX + "couponRule_add.html";
    }

    /**
     * 跳转到修改优惠劵规则
     */
    @RequestMapping("/couponRule_update/{couponRuleId}")
    public String couponRuleUpdate(@PathVariable String couponRuleId, Model model) {
       CouponRuleEntity bean = couponRuleService.getById(couponRuleId);
        model.addAttribute("couponRule",bean);
        return PREFIX + "couponRule_edit.html";
    }

    /**
     * 获取优惠劵规则列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CouponRuleEntity entity) {
        Page page = couponRuleService.findList(entity);
        return new PageInfo<>((List<CouponRuleEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增优惠劵规则
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestBody @Validated CouponRuleEntity entity) {
        couponRuleService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除优惠劵规则
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String couponRuleId) {
        couponRuleService.deleteById(couponRuleId);
        return SUCCESS_TIP;
    }


    /**
     * 修改优惠劵规则
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody @Validated CouponRuleEntity entity) {
        couponRuleService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 优惠劵规则详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return couponRuleService.getById(id);
    }
}
