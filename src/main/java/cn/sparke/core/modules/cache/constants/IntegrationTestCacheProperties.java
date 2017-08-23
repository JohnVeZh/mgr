package cn.sparke.core.modules.cache.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhangbowen on 2017/7/14.
 */
@ConfigurationProperties(prefix = "project.cache")
@Component
public class IntegrationTestCacheProperties {
    public String url;
    public int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
