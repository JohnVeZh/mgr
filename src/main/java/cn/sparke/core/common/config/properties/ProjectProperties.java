package cn.sparke.core.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * spark项目配置
 *
 * @author sparke
 * @Date 2017/5/23 22:31
 */
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {
    public String cache;
    private Boolean kaptchaOpen = false;

    private Integer sessionInvalidateTime = 30 * 60;  //session 失效时间（默认为30分钟 单位：秒）

    private Integer sessionValidationInterval = 15 * 60;  //session 验证失效时间（默认为15分钟 单位：秒）
    public String cacheSuffix;

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public Boolean getKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }
}
