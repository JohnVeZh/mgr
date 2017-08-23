package cn.sparke.modules.coupon.couponCode.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.coupon.couponCode.entity.BatchCouponCodeVo;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.coupon.couponCode.service.CouponCodeService;
import cn.sparke.modules.coupon.couponCode.entity.CouponCodeEntity;

import java.util.List;

/**
 * 优惠码控制器
 *
 * @author spark
 * @Date 2017-07-26 14:22:24
 */
@Controller
@RequestMapping("/couponCode")
public class CouponCodeController extends BaseController {

    private String PREFIX = "/couponCode/";

     @Autowired
     private CouponCodeService couponCodeService;

    /**
     * 跳转到优惠码首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "isSelect", defaultValue = "false") boolean isSelect, Model model) {
        model.addAttribute("isSelect", isSelect);
        return PREFIX + "couponCode.html";
    }

    /**
     * 跳转到添加优惠码
     */
    @RequestMapping("/batch_add")
    public String couponCodeBatchAdd() {
        return PREFIX + "couponCode_batch_add.html";
    }

    /**
     * 跳转到添加优惠码
     */
    @RequestMapping("/couponCode_add")
    public String couponCodeAdd() {
        return PREFIX + "couponCode_add.html";
    }

    /**
     * 跳转到修改优惠码
     */
    @RequestMapping("/couponCode_update/{couponCodeId}")
    public String couponCodeUpdate(@PathVariable String couponCodeId, Model model) {
       CouponCodeEntity bean = couponCodeService.getById(couponCodeId);
        model.addAttribute("couponCode",bean);
        return PREFIX + "couponCode_edit.html";
    }

    /**
     * 获取优惠码列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CouponCodeEntity entity) {
        Page page = couponCodeService.findList(entity);
        return new PageInfo<>((List<CouponCodeEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增优惠码
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated CouponCodeEntity entity) {
        couponCodeService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 新增优惠码
     */
    @RequestMapping(value = "/batch/add")
    @ResponseBody
    public Object batchAdd(@RequestBody @Validated BatchCouponCodeVo entity) {
        couponCodeService.batchSave(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除优惠码
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String couponCodeId) {
        couponCodeService.deleteById(couponCodeId);
        return SUCCESS_TIP;
    }


    /**
     * 修改优惠码
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody CouponCodeEntity entity) {
        couponCodeService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 优惠码详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return couponCodeService.getById(id);
    }
}
