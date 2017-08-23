package cn.sparke.modules.system.dept.controller;

import cn.sparke.core.common.constants.dictmap.DeptDict;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.dept.entity.Dept;
import cn.sparke.modules.system.dept.mapper.DeptMapper;
import cn.sparke.modules.system.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门控制器
 *
 * @author spark
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/system/dept/";

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptService deptService;

    /**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept.html";
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "dept_add.html";
    }

    /**
     * 跳转到修改部门
     */
    @Permission
    @RequestMapping("/dept_update/{deptId}")
    public String deptUpdate(@PathVariable String deptId, Model model) {
        Dept dept = deptMapper.getById(deptId);
        model.addAttribute(dept);
        LogObjectHolder.me().set(dept);
        return PREFIX + "dept_edit.html";
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptMapper.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 新增部门
     */
    @BussinessLog(value = "添加部门", key = "simplename", dict = DeptDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(@Validated Dept dept) {
        //完善pids,根据pid拿到pid的pids
        deptSetPids(dept);
        dept.preInsert();
        return this.deptMapper.insert(dept);
    }

    /**
     * 获取所有部门列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(Dept dept) {
        return this.deptMapper.findList(dept);
    }

    /**
     * 部门详情
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("deptId") String deptId) {
        return deptMapper.getById(deptId);
    }

    /**
     * 修改部门
     */
    @BussinessLog(value = "修改部门", key = "simplename", dict = DeptDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public Object update(@Validated Dept dept) {
        deptSetPids(dept);
        dept.preUpdate();
        deptMapper.update(dept);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除部门
     */
    @BussinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam String deptId) {
        //缓存被删除的部门名称
        LogObjectHolder.me().set(this.deptMapper.getById(deptId).getFullname());
        deptService.deleteDept(deptId);
        return SUCCESS_TIP;
    }

    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals("0")) {
            dept.setPid("0");
            dept.setPids("[0],");
        } else {
            String pid = dept.getPid();
            Dept temp = deptMapper.getById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}
