package cn.sparke.modules.goods.networkCourse.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.networkCourse.entity.NetworkCourseEntity;
import cn.sparke.modules.goods.networkCourse.service.NetworkCourseService;
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
 * 网课控制器
 *
 * @author spark
 * @Date 2017-07-22 16:13:33
 */
@Controller
@RequestMapping("/goods/networkCourse")
public class NetworkCourseController extends BaseController {

    private String PREFIX = "/goods/networkCourse/";

     @Autowired
     private NetworkCourseService networkCourseService;

    /**
     * 跳转到网课首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "networkCourse.html";
    }

    /**
     * 跳转到添加网课
     */
    @RequestMapping("/networkCourse_add")
    public String networkCourseAdd() {
        return PREFIX + "networkCourse_add.html";
    }

    /**
     * 跳转到修改网课
     */
    @RequestMapping("/networkCourse_update/{networkCourseId}")
    public String networkCourseUpdate(@PathVariable String networkCourseId, Model model) {
       NetworkCourseEntity bean = networkCourseService.getById(networkCourseId);
        model.addAttribute("networkCourse",bean);
        return PREFIX + "networkCourse_edit.html";
    }
    /**
     * 跳转到网课上架状态
     */
    @RequestMapping("/networkCourse_isShow/{networkCourseId}")
    public String networkCourse_isShow(@PathVariable String networkCourseId, Model model) {
       NetworkCourseEntity bean = networkCourseService.getById(networkCourseId);
        model.addAttribute("networkCourse",bean);
        return PREFIX + "networkCourse_isShow.html";
    }

    /**
     * 跳转到修改网课状态
     */
    @RequestMapping("/networkCourse_status/{networkCourseId}")
    public String networkCourseUpdateStatus(@PathVariable String networkCourseId, Model model) {
        NetworkCourseEntity bean = networkCourseService.getById(networkCourseId);
        model.addAttribute("networkCourse",bean);
        return PREFIX + "networkCourse_status.html";
    }

    /**
     * 获取网课列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(NetworkCourseEntity entity) {
        Page<NetworkCourseEntity> list = networkCourseService.queryAllList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 新增网课
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated NetworkCourseEntity entity) {
        networkCourseService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除网课
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String networkCourseId) {
        networkCourseService.deleteById(networkCourseId);
        return SUCCESS_TIP;
    }


    /**
     * 修改网课
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated NetworkCourseEntity entity) {
        networkCourseService.update(entity);
        return super.SUCCESS_TIP;
    }
    /**
     * 修改网课状态
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object updateStatus(@Validated NetworkCourseEntity entity) {
        networkCourseService.updateStatus(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 网课详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return networkCourseService.getById(id);
    }
}
