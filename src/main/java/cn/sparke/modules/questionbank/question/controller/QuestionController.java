package cn.sparke.modules.questionbank.question.controller;


import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.service.PaperService;
import cn.sparke.modules.paper.paper.wrapper.PaperWrapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.questionbank.question.entity.PaperSubtitleEntity;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question.service.QuestionService;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;


import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目控制器
 *
 * @author spark
 * @Date 2017-07-19 12:30:16
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {

    private String PREFIX = "/questionbank/question/";

     @Autowired
     private QuestionService questionService;

//    @Autowired
//    private PaperService paperService;

    /**
     * 跳转到题目首页question_update
     */
    @RequestMapping("")
    public String index(String type, Model model) {
        if(ToolUtil.isEmpty(type)){
            type="1";
        }
        model.addAttribute("type", type);
        model.addAttribute("code", "");
        return PREFIX + "question.html";
    }

    /**
     * 跳转到试卷详情
     */
    @RequestMapping("/index2")
    public String index2(String id, String type1,Integer contentType,Model model) {
        if(ToolUtil.isEmpty(id)){
            id="";
        }
        model.addAttribute("id", id);
        model.addAttribute("type", type1);
        model.addAttribute("contentType", contentType);

        return PREFIX + "paper_detail.html";
    }

    /**
     * 跳转到信息题目管理
     */
    @RequestMapping("/question_manage")
    public String questionManage(String paperId,String type ,String sectionCode ,String contentType,Model model){
        model.addAttribute("paperId",paperId);
        model.addAttribute("type",type);
        model.addAttribute("contentType",contentType);
        model.addAttribute("sectionCode",sectionCode);
        return PREFIX+"questionManage.html";
    }

    /**
     * 跳转到添加题目
     */
    @RequestMapping("/question_add")
    public String questionAdd(String paperId,String type ,String sectionCode ,String contentType,Model model){
        model.addAttribute("paperId",paperId);
        model.addAttribute("type",type);
        model.addAttribute("contentType",contentType);
        model.addAttribute("sectionCode",sectionCode);
        return PREFIX + "question_add.html";
    }

    /**
     * 跳转到修改题目
     */
    @RequestMapping("/question_update")
    public String questionUpdate(String id, Model model) {
       QuestionEntity bean = questionService.getById(id);
        model.addAttribute("question",bean);
        return PREFIX + "question_edit.html";
    }

    /**
     * 跳转到管理页面
     */
    @RequestMapping("/goList")
    public String goList(Integer type,String paperId, Model model) {
        model.addAttribute("paperId", paperId);
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/question.html";
                break;
            }
        }
        return html;
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/goAdd")
    public String goAdd(Integer type) {
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/questionAdd.html";
                break;
            }
        }
        return html;
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/goUpdate/{questionId}")
    public String goUpdate(@PathVariable String question,Integer type, Model model) {
        QuestionEntity bean = questionService.getById(question);
        model.addAttribute("questionEntity",bean);
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/questionEdit.html";
                break;
            }
        }
        return html;
    }

    /**
     * 获试题列表
     */
    @RequestMapping(value = "/pageList")
    @ResponseBody
    public Object getPaper(Integer type,QuestionEntity entity) {
        entity.setType(type);
        if(type ==5|| type ==2){
            List<PaperSubtitleEntity> list=questionService.findPaperQrList(entity);
            return super.warpObject(new PaperWrapper(list));
        }else{
            List<PaperEntity> list=questionService.findPaperList(entity);
            return super.warpObject(new PaperWrapper(list));
        }

    }

    /**
     * 获取题目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String paperId,QuestionEntity entity) {
        //通过试卷id获取相应的题目
        entity.setPaperId(paperId);
        return questionService.findList(entity);
    }

    /**
     * 新增题目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QuestionEntity entity) {
        questionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除题目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String questionId) {
        questionService.deleteById(questionId);
        return SUCCESS_TIP;
    }

    /**
     * 查询
     */
    @RequestMapping(value = "/getType")
    @ResponseBody
    public Object getType(String id) {
        Map<String,Object> map = questionService.getType(id);
        return map;
    }


    /**
     * 修改题目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QuestionEntity entity) {
        questionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 题目详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return questionService.getById(id);
    }



    /**
     * 试卷树形结构
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> menuTreeListByRoleId( Integer type,String sectionCodes) {

        Map<String,Object> dateMap = new HashMap<String,Object>();
        dateMap.put("type",type);
        List<Integer> sectionList = new ArrayList<Integer>();
        if(sectionCodes !=null && !sectionCodes.equals("")){
            if(sectionCodes.equals("0")){
                dateMap.put("loopBz",0);//表示全部学段
            }else {
                dateMap.put("loopBz",1);//表示有多个学段
            }
            String[] section=sectionCodes.split(",");
            for(String str: section){
                sectionList.add(Integer.valueOf(str));
            }
            dateMap.put("sectionList",sectionList);

        }
        //List<ZTreeNode> tree=questionService.sectionTreeList(type,sectionList);
        List<ZTreeNode> tree=questionService.sectionTreeList(dateMap);
//        tree.add(ZTreeNode.createParent());
        return  tree;

    }

    /**
     * 题目类型结构树
     */
    @RequestMapping(value = "/paperStructureTree")
    @ResponseBody
    public List<ZTreeNode>paperStructureTree( String id,Integer type,Integer contentType) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",id);
        paramMap.put("type",type);
        paramMap.put("contentType",contentType);
        List<ZTreeNode> tree = questionService.paperStructureTree(paramMap);
//        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 跳转文件上传页面
     */
    @RequestMapping(value = "/Upload")
    public String Upload(String paperId,Integer type,Model model,Integer contentType){
        model.addAttribute("type", type);
        model.addAttribute("paperId", paperId);
        model.addAttribute("contentType", contentType);
        return PREFIX + "FileUpload.html";

    }

    /**
     * 通过structureId查询该节点是否叶子节点
     */
    @RequestMapping(value = "/getPaperStructureById")
    @ResponseBody
    public String getPaperStructureById (@PathParam("structureId") String structureId){
        String message="";
        Map<String,String> returnMap = new HashMap<String,String>();
        PaperStructureEntity paperStructureEntity = questionService.getPaperStructureById(structureId);
        if(paperStructureEntity !=null && paperStructureEntity.getIsLeaf() !=null && paperStructureEntity.getIsLeaf()==1){
            message="1";
        }else {
            message="0";
        }
        return message;
    }




}







