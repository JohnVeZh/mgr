package cn.sparke.modules.paper.paper.controller;

import cn.sparke.core.common.constants.tips.ErrorTip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.service.PaperService;
import cn.sparke.modules.paper.paper.wrapper.PaperWrapper;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.paper.paperCatalog.service.PaperCatalogService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 试卷控制器
 *
 * @author spark
 * @Date 2017-07-19 09:50:52
 */
@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {

    private String PREFIX = "/paper/paper/";

     @Autowired
     private PaperService paperService;

     @Autowired
     private PaperCatalogService paperCatalogService;

    /**
     * 跳转到试卷首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paper.html";
    }

    /**
     * 跳转到试卷列表
     */
    @RequestMapping("/{type}")
    public String go(@PathVariable Integer type) {
        String html = StringUtils.EMPTY;
        switch (type) {
            case 6 : {
                html = "/gift/paper/giftPaper.html";
                break;
            }
        }
        return html;
    }

    /**
     * 跳转到上传
     */
    @RequestMapping("/upload")
    public String paperUpload() {
        return PREFIX + "upload.html";
    }

    /**
     * 跳转到添加试卷
     */
    @RequestMapping("/paper_add")
    public String paperAdd() {
        return PREFIX + "paper_add.html";
    }

    /**
     * 跳转到修改试卷
     */
    @RequestMapping("/paper_update/{paperId:.+}")
    public String paperUpdate(@PathVariable String paperId, Model model) {
       PaperEntity bean = paperService.getById(paperId);
        model.addAttribute("paper",bean);
        return PREFIX + "paper_edit.html";
    }

    /**
     * 获取试卷列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperEntity entity) {
        Page page = paperService.findList(entity);
        return new PageInfo<>((List<PaperEntity>) new PaperWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增试卷
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PaperEntity entity) {
        paperService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除试卷
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        paperService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改试卷
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperEntity entity) {
        paperService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 试卷详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return paperService.getById(id);
    }
    /**
     * 查询试卷类型
     */
    @RequestMapping("/paperType")
    @ResponseBody
    public String paperType(@RequestParam("id")String id){
        PaperEntity paperEntity =  paperService.getById(id);

        String paperType="";
        if(paperEntity!=null && paperEntity.getCatalogId()!=null){
            PaperCatalogEntity paperCatalogEntity = paperCatalogService.getById(paperEntity.getCatalogId());
            if(paperCatalogEntity!=null){
                paperType = String.valueOf(paperCatalogEntity.getType());
            }
        }
        return paperType;
    }
    /**
     * 统计专项练习试卷题目数量
     * @return
     */
    @RequestMapping(value = "/refSpeciaQuestionCount")
    @ResponseBody
    public Object refSpeciaQuestionCount() {
        try {
            paperService.refSpeciaQuestionCount();
        } catch (BussinessException e) {
            return new ErrorTip(e.getCode(),e.getMessage());
        } catch (Exception e) {
            return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 模板下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/template")
    @ResponseBody
    public Object template(String type,HttpServletRequest request, HttpServletResponse response) {
        try {
            paperService.template(type, request, response);
        } catch (BussinessException e) {
            return new ErrorTip(e.getCode(),e.getMessage());
        } catch (Exception e) {
            return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
        }
        return super.SUCCESS_TIP;
    }

    /**
     * 模板导入
     * @param paperId
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/{paperId}")
    @ResponseBody
    public Object upload(@PathVariable String paperId, @RequestParam("file") MultipartFile file) {
        try {
            paperService.upload(paperId, file);
        } catch (BussinessException e) {
            return new ErrorTip(e.getCode(),e.getMessage());
        } catch (Exception e) {
            return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
        }
        return super.SUCCESS_TIP;
    }

}
