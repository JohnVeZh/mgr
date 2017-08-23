package cn.sparke.modules.system.upload.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.qiniu.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by zhangbowen on 2017/7/18.
 */
@RequestMapping("/system")
@Controller
public class UploadController extends BaseController {
    @Autowired
    private QiniuService qiniuService;

    /**
     * 上传图片
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {
        String picUrl;
        try {
            picUrl = qiniuService.upload(picture.getOriginalFilename(), picture.getInputStream());
        } catch (IOException e) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return picUrl;
    }

    /**
     * 上传图片
     */
    @RequestMapping(value = "/upload/multi", method = RequestMethod.POST)
    @ResponseBody
    public UploadFile uploadMulti(MultipartHttpServletRequest request) {
        try {
           return qiniuService.uploadMulti(request);
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
    }

    /**
     * 上传图片
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean delete() {
        return true;
    }
}
