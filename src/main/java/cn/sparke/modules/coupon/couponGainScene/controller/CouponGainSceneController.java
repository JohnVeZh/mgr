package cn.sparke.modules.coupon.couponGainScene.controller;

import cn.sparke.core.common.constants.tips.ErrorTip;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.coupon.couponGainScene.entity.vo.CouponGainSceneVo;
import cn.sparke.modules.coupon.couponRule.entity.CouponRuleEntity;
import cn.sparke.modules.coupon.couponRule.service.CouponRuleService;
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
import cn.sparke.modules.coupon.couponGainScene.service.CouponGainSceneService;
import cn.sparke.modules.coupon.couponGainScene.entity.CouponGainSceneEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import cn.sparke.core.common.exception.BizExceptionEnum;

/**
 * 优惠劵业务场景控制器
 *
 * @author spark
 * @Date 2017-07-26 16:15:50
 */
@Controller
@RequestMapping("/couponGainScene")
public class CouponGainSceneController extends BaseController {

    private String PREFIX = "/couponGainScene/";


    @Autowired
    private CouponRuleService couponRuleService;
    @Autowired
    private CouponGainSceneService couponGainSceneService;

    /**
     * 跳转到优惠劵业务场景首页
     */
    @RequestMapping("/{couponRuleId}")
    public String index(@PathVariable("couponRuleId") String couponRuleId, Model model) {
        CouponRuleEntity couponRuleEntity = couponRuleService.getById(couponRuleId);
        model.addAttribute("couponRuleEntity", couponRuleEntity);
        model.addAttribute("couponRuleId", couponRuleId);
        return PREFIX + "couponGainScene.html";
    }

    /**
     * 跳转到添加优惠劵业务场景
     */
    @RequestMapping("/{couponRuleId}/add")
    public String couponGainSceneAdd(@PathVariable("couponRuleId") String couponRuleId, Model model) {
        CouponRuleEntity couponRuleEntity = couponRuleService.getById(couponRuleId);
        model.addAttribute("couponRuleEntity", couponRuleEntity);
        model.addAttribute("couponRuleId", couponRuleId);
        return PREFIX + "couponGainScene_add.html";
    }

    /**
     * 跳转到修改优惠劵业务场景
     */
    @RequestMapping("/couponGainScene_update/{couponGainSceneId}")
    public String couponGainSceneUpdate(@PathVariable String couponGainSceneId, Model model) {
        CouponGainSceneEntity bean = couponGainSceneService.getById(couponGainSceneId);
        model.addAttribute("couponGainScene", bean);
        return PREFIX + "couponGainScene_edit.html";
    }

    /**
     * 获取优惠劵业务场景列表
     */
    @RequestMapping(value = "/{couponRuleId}/list")
    @ResponseBody
    public Object list(@PathVariable String couponRuleId, CouponGainSceneEntity entity) {
        entity.setRuleId(couponRuleId);
        Page page = couponGainSceneService.findList(entity);
        return new PageInfo<>((List<CouponGainSceneEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增优惠劵业务场景
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestBody @Validated CouponGainSceneVo entity) {
        CouponGainSceneVo newSearch = new CouponGainSceneVo();
        newSearch.setContentId(entity.getContentId());
        newSearch.setType(entity.getType());
        newSearch.setProductIdList(entity.getProductIdList());
        Page<CouponGainSceneEntity> searchPaper = couponGainSceneService.findVoList(newSearch);
        if (searchPaper != null && searchPaper.size() > 0) {
            BussinessException exception = new BussinessException(BizExceptionEnum.COUPON_GAIN_SCENE);
            String errorMsg = exception.getMessage();
            String productName = String.join(",", searchPaper.stream().map(CouponGainSceneEntity::getProductName).collect(Collectors.toList()));
            exception.setMessage(errorMsg + "重复的商品名称：" + productName);
            throw exception;
        }

        couponGainSceneService.batchSave(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除优惠劵业务场景
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String couponGainSceneId) {
        couponGainSceneService.deleteById(couponGainSceneId);
        return SUCCESS_TIP;
    }


    /**
     * 修改优惠劵业务场景
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CouponGainSceneEntity entity) {
       /* CouponGainSceneEntity newSearch = new CouponGainSceneEntity();
        newSearch.setContentId(entity.getContentId());
        newSearch.setType(entity.getType());
        Page<CouponGainSceneEntity> searchPaper = couponGainSceneService.findList(newSearch);
        if (searchPaper != null && searchPaper.size() > 0){
            throw new BussinessException(BizExceptionEnum.COUPON_GAIN_SCENE);
        }*/
        couponGainSceneService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 优惠劵业务场景详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return couponGainSceneService.getById(id);
    }
}
