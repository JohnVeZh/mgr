package cn.sparke.modules.questionbank.FileUpload.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by douht on 2017/7/28.
 */
@Service
public class FileUploadService {

    public Integer downloadFile(Integer type,HttpServletResponse res) {
        Integer message=0;
        String fileName = "";
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream out = null;
        String path="classpath:template/paperExcel/";
        String filePath="";
        switch (type){
            case 1 :{
                //全真听力
                fileName="全真听力模板.xls";

                filePath= path+"question_true_listening.xls";
                break;
            }
            case 2:{
                fileName="全真阅读模板.xls";
                //全真阅读
                filePath= path+ "question_true_reading.xls";
                break;
            }
            case 3: {
                fileName="全真翻译模板.xls";
                //全真翻译
                filePath= path+ "question_true_translation.xls";
                break;
            }
            case 4:{
                fileName="全真写作模板.xls";
                //全真写作
                filePath= path+"question_true_writing.xls";
                break;
            }
            case 5:{
                fileName="字幕听力模板.xls";
                //字幕听力
                filePath= path+"question_subtitle_listening.xls";
                break;
            }
            case 6:{
                fileName="简系列字幕听力模板.xls";
                //字幕听力
                filePath= path+"simple_caption_listening_template.xls";
                break;
            }
        }

        try {
            //转码
            byte[] nameByte=fileName.getBytes("UTF-8");
            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + new String(nameByte,"ISO-8859-1"));
            out = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                out.write(buff, 0, buff.length);
                out.flush();
                i = bis.read(buff);
            }
            message=1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return message;
    }


}
