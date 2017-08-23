package cn.sparke.core.modules.qiniu.bean;

/**
 * Created by zhangbowen on 2017/7/8.
 */
public class ResultTokenBean {
    private String token;

    public ResultTokenBean() {
    }

    public ResultTokenBean(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
