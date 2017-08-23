package cn.sparke.core.modules.qiniu.service;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.qiniu.constants.QiNiuProperties;
import cn.sparke.modules.system.upload.controller.UploadFile;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by zhangbowen on 2017/7/18.
 */
@Service
public class QiniuService {

    private Auth auth = null;
    @Autowired
    private QiNiuProperties qiNiuProperties;

    @PostConstruct
    public void init() {
        if (!StringUtils.isEmpty(qiNiuProperties.access_key_id) && !StringUtils.isEmpty(qiNiuProperties.access_key_secret)) {
            auth = Auth.create(qiNiuProperties.access_key_id, qiNiuProperties.access_key_secret);
        }
    }

    public String getToken() {
        String upToken = auth.uploadToken(qiNiuProperties.bucket);
        return upToken;
    }

    public String upload(String originalFileName, InputStream inputStream) {//构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = ToolUtil.uuid();
        String extensions = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
        if ("png".equalsIgnoreCase(extensions) || "jpg".equalsIgnoreCase(extensions) || "jpeg".equalsIgnoreCase(extensions)) {
            key = key + ".png";
        } else if ("mp3".equalsIgnoreCase(extensions) || "wav".equalsIgnoreCase(extensions) ) {
            key = key + ".mp3";
        }
        String upToken = auth.uploadToken(qiNiuProperties.bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qiNiuProperties.domain + putRet.key;
        } catch (QiniuException ex) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
    }

    public UploadFile uploadMulti(MultipartHttpServletRequest request) throws IOException {
        String key = ToolUtil.uuid();
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String upToken = auth.uploadToken(qiNiuProperties.bucket);
        Iterator<String> itr = request.getFileNames();
        if (itr.hasNext()) {
            MultipartFile mpf = request.getFile(itr.next());
            String originalFileName = mpf.getOriginalFilename();
            String extensions = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
            if ("png".equalsIgnoreCase(extensions) || "jpg".equalsIgnoreCase(extensions) || "jpeg".equalsIgnoreCase(extensions)) {
                key = key + ".png";
            } else if ("mp3".equalsIgnoreCase(extensions) || "wav".equalsIgnoreCase(extensions) ) {
                key = key + ".mp3";
            }
            Response response = uploadManager.put(mpf.getInputStream(), key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return new UploadFile(qiNiuProperties.domain + putRet.key);
        }
        return null;
    }
}
