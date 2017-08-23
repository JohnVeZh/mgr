package cn.sparke.modules.orders.refund.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.orders.refund.entity.RefundDetailsEntity;
import cn.sparke.modules.orders.refund.mapper.RefundMapper;
import cn.sparke.modules.orders.refund.service.RefundService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.orders.refund.entity.RefundEntity;

/**
 * 退单控制器
 *
 * @author spark
 * @Date 2017-07-20 09:54:27
 */
@Controller
@RequestMapping("/orders/refund")
public class RefundController extends BaseController {

    private String PREFIX = "/orders/refund/";

    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private RefundService refundService;

    /**
     * 跳转到退单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "refund.html";
    }


    /**
     * 获取退单列表，分页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(RefundEntity refundEntity) {
        Page<RefundEntity> refundList = this.refundService.findList(refundEntity);
        return new PageInfo<>(refundList);
    }

    /**
     * 跳转到修改退单
     */
    @RequestMapping("/refund_update/{refundId}")
    public String refundUpdate(@PathVariable String refundId, Model model) {
      RefundDetailsEntity bean = refundService.getById(refundId);
       model.addAttribute("refund",bean);
        return PREFIX + "refund_details.html";
    }

}
