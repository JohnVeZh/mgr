package cn.sparke.modules.paper.paperStructure.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.paper.paperStructure.service.PaperStructureService;
import cn.sparke.modules.paper.paperStructure.wrapper.PaperStructureWapper;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
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
 * 试卷树形结构控制器
 *
 * @author spark
 * @Date 2017-07-19 09:52:16
 */
@Controller
@RequestMapping("/paper/paperStructure")
public class PaperStructureController extends BaseController {

    private String PREFIX = "/paper/paperStructure/";

    @Autowired
    private PaperStructureService paperStructureService;

    @Autowired
    private PaperStructureMapper paperStructureMapper;

    /**
     * 跳转到试卷树形结构首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paperStructure.html";
    }

    /**
     * 跳转到添加试卷树形结构
     */
    @RequestMapping("/paperStructure_add")
    public String paperStructureAdd() {
        return PREFIX + "paperStructure_add.html";
    }

    /**
     * 跳转到试卷结构管理页面
     */
    @RequestMapping("/paperStructureList")
    public String paperStructureList(@RequestParam("paperId") String paperId, Model model) {


        model.addAttribute("paperId", paperId);

        return PREFIX + "paperStructureList.html";
    }

    /**
     * 跳转到试卷结构管理页面
     */
    @RequestMapping("/goList")
    public String goList(Integer type,String paperId, Model model) {
        model.addAttribute("paperId", paperId);
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/paperStructure.html";
                break;
            }
        }
        return html;
    }

    /**
     * 跳转到试卷结构添加
     */
    @RequestMapping("/goAdd")
    public String goAdd(Integer type) {
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/paperStructureAdd.html";
                break;
            }
        }
        return html;
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/goUpdate/{paperStructureId}")
    public String goUpdate(@PathVariable String paperStructureId,Integer type, Model model) {
        PaperStructureEntity bean = paperStructureService.getById(paperStructureId);
        model.addAttribute("paperStructureEntity",bean);
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/paperStructureEdit.html";
                break;
            }
        }
        return html;
    }

    /**
     * 跳转到修改试卷树形结构
     */
    @RequestMapping("/paperStructure_update/{paperStructureId}")
    public String paperStructureUpdate(@PathVariable String paperStructureId, Model model) {
        PaperStructureEntity bean = paperStructureService.getById(paperStructureId);
        model.addAttribute(bean);
        return PREFIX + "paperStructure_edit.html";
    }

    /**
     * 获取试卷树形结构列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperStructureEntity entity) {
        return paperStructureService.findList(entity);
    }

    /**
     * 获取试卷树形结构列表-分页
     */
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Object queryList(PaperStructureEntity entity) {
        Page page = paperStructureService.findList(entity);
        return new PageInfo<>((List<PaperStructureEntity>) new PaperStructureWapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 获取结构树
     */
    @RequestMapping(value = "/structureTree")
    @ResponseBody
    public List<ZTreeNode> structureTree(PaperStructureEntity entity) {
        return paperStructureService.structureTree(entity);
    }

    /**
     * 新增试卷树形结构
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PaperStructureEntity entity) {

        paperStructureService.save(entity);

        return super.SUCCESS_TIP;
    }

    /**
     * 新增试卷树形结构
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public Object insert(@Validated PaperStructureEntity entity) {
        paperStructureService.insert(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除试卷树形结构
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        paperStructureService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改试卷树形结构
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperStructureEntity entity) {
        paperStructureService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 检查试卷结构
     */
    @RequestMapping("/check")
    @ResponseBody
    public String check(String id) {
        return paperStructureService.check(id);
    }

    /**
     * 试卷树形结构详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return paperStructureService.getById(id);
    }

    @RequestMapping(value = "/toDetailStructure/{id}")
    public String toDetailStructure(@PathVariable("id") String id, Model model) {
        PaperStructureEntity entity = paperStructureService.getById(id);

        model.addAttribute("paperStructureEntity", entity);

        return PREFIX + "paperStructure_edit.html";
    }

    /**
     * 试卷结构管理页面根据试卷获取结构类型
     */
    @RequestMapping(value = "/getStructureByPaper")
    @ResponseBody
    public Object getStructureByPaper(PaperStructureEntity paperStructureEntity) {

        List<PaperStructureEntity> list = this.paperStructureService.findList(paperStructureEntity);

        String[] rootArray = {"听力", "阅读", "翻译", "写作"};

        PaperStructureEntity root;

        for (int i = 1; i <= rootArray.length; i++) {
            root = new PaperStructureEntity();
            root.setName(rootArray[i - 1]);
            root.setId(String.valueOf(i));
            root.setLevel(0);
            if (list != null && list.size() > 0) {
                for (PaperStructureEntity entity : list) {
                    if (entity.getContentType() != null && entity.getParentId() != null) {
                        if (entity.getParentId().equals("0") && entity.getContentType() == i) {
                            entity.setParentId(String.valueOf(i));
                        }
                    }
                }
            }
            list.add(root);
        }
        Object o = super.warpObject(new PaperStructureWapper(list));
        return o;
    }

    /**
     * 跳转到添加试卷结构页面
     */
    @RequestMapping("/toAddPaperStructure")
    public String toAddPaperStructure(@RequestParam("paperId") String paperId, Model model) {
        model.addAttribute("paperId", paperId);
        return PREFIX + "paperStructure_add.html";
    }

    /**
     * 获取父节点树(添加试卷结构时获取)
     *
     * @param paperId
     * @return
     */
    @RequestMapping("/getParentStructure")
    @ResponseBody
    public List<ZTreeNode> getParentStructure(@RequestParam(value = "paperId", required = true) String paperId) {
        List<ZTreeNode> treeNodeList = this.paperStructureMapper.treeList(paperId);

       /* if(treeNodeList == null || treeNodeList.size()==0){
            List<ZTreeNode> headList = ZTreeNode.createStructureParent();
            treeNodeList.addAll(headList);
        }*/

        return treeNodeList;
    }

}
