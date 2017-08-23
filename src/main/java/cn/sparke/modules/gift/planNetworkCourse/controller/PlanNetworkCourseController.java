package cn.sparke.modules.gift.planNetworkCourse.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.gift.planConfig.entity.PlanConfigEntity;
import cn.sparke.modules.gift.planConfig.service.PlanConfigService;
import cn.sparke.modules.gift.planNetworkCourse.entity.PlanNetworkCourseEntity;
import cn.sparke.modules.gift.planNetworkCourse.service.PlanNetworkCourseService;
import cn.sparke.modules.goods.networkCourse.service.NetworkCourseService;
import cn.sparke.modules.goods.product.service.ProductService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 学习方案绑定网课列表控制器
 *
 * @author spark
 * @Date 2017-08-21 14:57:58
 */
@Controller
@RequestMapping("/gift/planNetworkCourse")
public class PlanNetworkCourseController extends BaseController {

    private String PREFIX = "/gift/planNetworkCourse/";
    private String planId = "";


     @Autowired
     private PlanNetworkCourseService planNetworkCourseService;
     @Autowired
     private PlanConfigService planConfigService;
     @Autowired
     private ProductService productService;
     @Autowired
     private NetworkCourseService networkCourseService;

    /**
     * 跳转到学习方案绑定网课列表首页
     */
    @RequestMapping("/{configId}")
    public String index(@PathVariable String configId) {
        this.planId = configId;
        return PREFIX + "planNetworkCourse.html";
    }

    /**
     * 跳转到添加学习方案绑定网课列表
     */
    @RequestMapping("/planNetworkCourse_add")
    public String planNetworkCourseAdd(Model model) {

        PlanConfigEntity bean = planConfigService.getById(planId);
        model.addAttribute("planConfig",bean);
        return PREFIX + "planNetworkCourse_add.html";
    }

    /**
     * 跳转到修改学习方案绑定网课列表
     */
    @RequestMapping("/planNetworkCourse_update/{planNetworkCourseId}")
    public String planNetworkCourseUpdate(@PathVariable String planNetworkCourseId, Model model) {

        PlanConfigEntity planConfigEntity = planConfigService.getById(planId);
        PlanNetworkCourseEntity bean = planNetworkCourseService.getById(planNetworkCourseId);
        String networkCourseId = bean.getNetworkCourseId();
        String name = productService.getById(networkCourseService.getById(networkCourseId).getProductId()).getName();
        model.addAttribute("planConfig",planConfigEntity);
        model.addAttribute("planNetworkCourse",bean);
        model.addAttribute("ncName",name);
        model.addAttribute("networkCourseId",networkCourseId);
        return PREFIX + "planNetworkCourse_edit.html";
    }

    /**
     * 获取学习方案绑定网课列表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PlanNetworkCourseEntity planNetworkCourseEntity) {

        if (!"".equals(planId)){
            PlanConfigEntity planConfigEntity = planConfigService.getById(planId);
            Integer sectionCode = planConfigEntity.getSectionCode();
            planNetworkCourseEntity.setSectionCode(sectionCode);
            planNetworkCourseEntity.setTitle(planConfigEntity.getTitle());
        }
        planNetworkCourseEntity.setPlanId(planId);
        Page<PlanNetworkCourseEntity> list = planNetworkCourseService.queryList(planNetworkCourseEntity);
        return new PageInfo<>(list);
    }

    /**
     * 新增学习方案绑定网课列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PlanNetworkCourseEntity entity) {
//        planNetworkCourseService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除学习方案绑定网课列表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String planNetworkCourseId) {
        planNetworkCourseService.deleteById(planNetworkCourseId);
        return SUCCESS_TIP;
    }


    /**
     * 修改学习方案绑定网课列表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PlanNetworkCourseEntity entity) {
        planNetworkCourseService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 学习方案绑定网课列表详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return planNetworkCourseService.getById(id);
    }
}
