package cn.sparke.core.modules.jiguang.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/23.
 */
@ConfigurationProperties(prefix = "project.jiguang")
@Component
public class JpushProperties {
    private String appKey;
    private String masterSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }
}
