package cn.sparke.core.modules.cache;

import cn.sparke.core.common.config.properties.ProjectProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import cn.sparke.core.modules.cache.bean.CacheBean;
import cn.sparke.core.modules.cache.service.CacheService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by zhangbowen on 2015/12/24.
 * 缓存管理,所有的缓存都为json格式
 */
@SuppressWarnings("unchecked")
public class CacheFacade {
    private final static Logger logger = Logger.getLogger(CacheFacade.class);
    // 缓存操作实例
    private static CacheService cache;
    private static String cache_suffix;
    private static CacheFacade cacheFacade;

    public static void shutdown() {
        if (cache != null) {
            cache.shutdown();
        }
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param exp   失效时间(秒)
     */
    public static void set(String key, Object value, int exp) {
        CacheBean cacheBean = new CacheBean();
        cacheBean.setDefaultType(value.getClass());
        cacheBean.setJsonValue(JSON.toJSONString(value));
        cache.set(cache_suffix + "_" + key, JSON.toJSONString(cacheBean), exp);
    }

    /**
     * 删除缓存数据
     *
     * @param key
     */
    public static void delete(String key) {
        cache.delete(cache_suffix + "_" + key);
    }

    /**
     * 从缓存获得对象
     *
     * @param key
     * @return
     */
    private static CacheBean getByCache(String key) {
        Object value = cache.get(cache_suffix + "_" + key);
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value.toString(), CacheBean.class);
    }


    /**
     * 获取缓存对象,解析为默认的class对象
     *
     * @param key
     * @return
     */
    public static <T> T getObject(String key) {
        CacheBean cacheBean = getByCache(key);
        if (cacheBean == null) {
            return null;
        }
        return (T) JSON.parseObject(cacheBean.getJsonValue(), cacheBean.getDefaultType());
    }

    /**
     * 获取缓存对象,解析为默认的class对象
     *
     * @param key
     * @return
     */
    public static <T> T getObject(String key, TypeReference<T> typeReference) {
        CacheBean cacheBean = getByCache(key);
        if (cacheBean == null) {
            return null;
        }
        return JSON.parseObject(cacheBean.getJsonValue(), typeReference);
    }

    /**
     * 获取缓存数据,如果关键字不存在返回null
     *
     * @param key
     * @return
     */
    public static <T> List<T> getList(String key, Class type) {
        CacheBean cacheBean = getByCache(key);
        if (cacheBean == null) {
            return null;
        }
        return JSON.parseArray(cacheBean.getJsonValue(), type);
    }

    static CacheFacade initCache(CacheService cacheService, ProjectProperties projectProperties) {
        cache = cacheService;
        cache_suffix = projectProperties.cacheSuffix;
        if (cacheFacade == null) {
            cacheFacade = new CacheFacade();
        }
        return cacheFacade;
    }
}
