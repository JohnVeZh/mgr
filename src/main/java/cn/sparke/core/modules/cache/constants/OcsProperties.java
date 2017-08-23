package cn.sparke.core.modules.cache.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhangbowen on 2017/7/8.
 * 必须要有get set 方法，不然无法注入
 */
@ConfigurationProperties(prefix = "project.ali.ocs")
@Component
public class OcsProperties {
    public String uid;
    public String pwd;
    public String url;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
