package cn.sparke.core.modules.qiniu.controller;

import cn.sparke.core.modules.qiniu.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
@Controller
@RequestMapping("/qiniu")
public class QiNiuController {
    @Autowired
    QiniuService qiniuService;

    @GetMapping("/getUpToken")
    @ResponseBody
    public ResponseEntity getUpToken(){
         String uptoken = qiniuService.getToken();
        Map<String,String> token = new HashMap<>();
        token.put("uptoken",uptoken);
        return ResponseEntity.ok(token);
    }

}
