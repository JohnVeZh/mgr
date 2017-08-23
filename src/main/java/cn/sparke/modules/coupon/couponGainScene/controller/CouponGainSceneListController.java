package cn.sparke.modules.coupon.couponGainScene.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.product.entity.ProductEntity;
import cn.sparke.modules.goods.product.service.ProductService;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/7/28 16:40
 */
@Controller
@RequestMapping("/couponGainScene")
public class CouponGainSceneListController extends BaseController {

    private String PREFIX = "/couponGainScene/view/";

    @Autowired
    private ProductService productService;


    //targetType,
    // 如果为1，则为contentId填充数据
    // 如果为2，则为productId填充数据
    @RequestMapping("/product/view")
    public String productview(@RequestParam(value = "targetType", defaultValue = "1") int targetType, Model model) {
        model.addAttribute("targetType", targetType);
        return PREFIX + "couponGainSceneProduct.html";
    }

    @ResponseBody
    @RequestMapping("/product/list")
    public Object productlist(@Validated ProductEntity entity, Model model) {
        String nameLike = entity.getNameLike();
        if (!Strings.isNullOrEmpty(nameLike)){
            entity.setName(nameLike);
        }
        Page page = productService.findList(entity);
        return new PageInfo<>((List<ProductEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }


}
