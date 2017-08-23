package cn.sparke.modules.system.upload.controller;

/**
 * Created by zhangbowen on 2015/6/24.
 * 图片
 */
public class UploadFile {
    private String url;

    public UploadFile(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
