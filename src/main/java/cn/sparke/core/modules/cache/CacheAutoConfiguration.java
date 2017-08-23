package cn.sparke.core.modules.cache;

import cn.sparke.core.common.config.properties.ProjectProperties;
import cn.sparke.core.modules.cache.constants.IntegrationTestCacheProperties;
import cn.sparke.core.modules.cache.constants.OcsProperties;
import cn.sparke.core.modules.cache.service.CacheService;
import cn.sparke.core.modules.cache.service.impl.AliMemCache;
import cn.sparke.core.modules.cache.service.impl.IntegrationTestCache;
import cn.sparke.core.modules.cache.service.impl.LocalCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangbowen on 2017/5/5.
 */
@Configuration
public class CacheAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CacheService.class)
    @ConditionalOnProperty(prefix = "project.cache", value = "level", havingValue = "ali")
    public CacheService aliCache(OcsProperties ocsProperties) {
        return new AliMemCache(ocsProperties.uid, ocsProperties.pwd, ocsProperties.url);
    }

    @Bean
    @ConditionalOnMissingBean(CacheService.class)
    @ConditionalOnProperty(prefix = "project.cache", value = "level", havingValue = "test")
    public CacheService testCache(IntegrationTestCacheProperties integrationTestCacheProperties) {
        return new IntegrationTestCache(integrationTestCacheProperties.url, integrationTestCacheProperties.port);
    }

    @Bean
    @ConditionalOnMissingBean(CacheService.class)
    public CacheService localCacheService() {
        return new LocalCache();
    }

    @Bean
    @ConditionalOnBean(CacheService.class)
    public CacheFacade initCache(CacheService cacheService, ProjectProperties projectProperties) {
        return CacheFacade.initCache(cacheService,projectProperties);
    }
}
