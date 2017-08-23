package cn.sparke.core.modules.qiniu.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhangbowen on 2017/7/8.
 * 必须要有get set 方法，不然无法注入
 */
@ConfigurationProperties(prefix = "project.qiniu")
@Component
public class QiNiuProperties {
    public String access_key_id;
    public String access_key_secret;
    public String bucket;
    public String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccess_key_id() {
        return access_key_id;
    }

    public void setAccess_key_id(String access_key_id) {
        this.access_key_id = access_key_id;
    }

    public String getAccess_key_secret() {
        return access_key_secret;
    }

    public void setAccess_key_secret(String access_key_secret) {
        this.access_key_secret = access_key_secret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
