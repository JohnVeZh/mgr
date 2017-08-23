package cn.sparke.modules.questionbank.FileUpload.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.mapper.PaperMapper;
import cn.sparke.modules.questionbank.FileUpload.service.FileUploadService;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question.service.QuestionSubtitleListService;
import cn.sparke.modules.questionbank.question.service.QuestionTrueService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by douht on 2017/7/27.
 */
@Controller
@RequestMapping("/FileUoload")
public class FileUoloadContrller extends BaseController {

    @Autowired
    private QuestionTrueService questionTrue;
    @Autowired
    private QuestionSubtitleListService questionSubtitleList;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private QuestionMapper questionMapper;



    /**
     * 文件上传具体实现方法（单文件上传）
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> upload(@RequestParam("file") MultipartFile file,@RequestParam String paperId,@RequestParam Integer type ,@RequestParam Integer contentType ) {
        Map<String,String> paramMap =new HashMap<String,String>();
        Map<String,String>  dataMap = new HashMap<String,String>();
        String code="";
        String message="";
        //获取试卷信息
        PaperEntity paperEntity=paperMapper.getById(paperId);
        Map<String,Object> sectionMap=questionMapper.getSectionCodeByPaperId(paperId);
        //获取学段
        if(type ==2 || type==5){
            sectionMap=questionMapper.getCaptionSectionCodeByPaperId(paperId).get(0);
        }else {
            sectionMap=questionMapper.getSectionCodeByPaperId(paperId);
        }
        paramMap.put("contentType",contentType.toString());//内容类型(1.听力2.阅读3.翻译4.写作;)
        paramMap.put("paperName",paperEntity.getName());//试卷名称
        paramMap.put("paperId",paperId);//试卷id
        paramMap.put("type",type.toString());//试卷类型（1.全真考场 2.字幕听力 3.简系列 4.专项练习,5.扫码字幕听力）
        paramMap.put("sectionCode",sectionMap.get("sectionCode").toString());//学段
        paramMap.put("starRow","2");//默认从第二行开始解析
        if (!file.isEmpty()) {
            try {
                HSSFWorkbook  wb = new HSSFWorkbook(file.getInputStream());
                if(type ==1){
                   dataMap = questionTrue.insertExcel(wb,paramMap);
                }else if(type==2){
                    dataMap = questionSubtitleList.insertExcel(wb,paramMap);
                }
                wb.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                code="902";
                message="上传失败," + e.getMessage();
                dataMap.put("code",code);
                dataMap.put("message",message);
            } catch (IOException e) {
                e.printStackTrace();
                code="902";
                message="上传失败," + e.getMessage();
                dataMap.put("code",code);
                dataMap.put("message",message);
            }
        } else {
            code="902";
            message="上传失败，因为文件是空的.";
            dataMap.put("code",code);
            dataMap.put("message",message);

        }
        return dataMap;
    }


    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public Object downloadFile(Integer type,HttpServletResponse response) {

       Integer message= fileUploadService.downloadFile(type ,response);
       if(message==1){
           return super.SUCCESS_TIP;
       }else {
           return super.ERROR;
       }

    }


    /**
     * 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
     */
    @RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
    @ResponseBody
    public String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }
        }
        return "upload successful";
    }


    @RequestMapping("/upload2")
    public String uploadFile2(@RequestParam("file") CommonsMultipartFile file,
                              HttpServletRequest request) throws IOException {
        // 定义解析器去解析request
        CommonsMultipartResolver mutilpartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //request如果是Multipart类型、
        if (mutilpartResolver.isMultipart(request)) {
            //强转成 MultipartHttpServletRequest
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取文件名
            Iterator<String> it = multiRequest.getFileNames();
            while (it.hasNext()) {
                //获取MultipartFile类型文件
                MultipartFile fileDetail = multiRequest.getFile(it.next());
                if (fileDetail != null) {
                    String fileName = "demoUpload" + fileDetail.getOriginalFilename();
                    String path = "D:/" + fileName;
                    File localFile = new File(path);
                    //将上传文件写入到指定文件出、核心！
                    fileDetail.transferTo(localFile);
                    //非常重要、有了这个想做什么处理都可以
                    //fileDetail.getInputStream();
                }
            }
        }
        return "/success";
    }


}
