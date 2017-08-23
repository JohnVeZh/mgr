package cn.sparke.core.modules.cache.service;

/**
 * Created by zhangbowen on 2017/5/5.
 */
public interface CacheService {
    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param exp   失效时间(秒)
     */
    void set(String key, Object value, int exp);

    /**
     * 删除缓存数据
     *
     * @param key
     */
    void delete(String key);

    /**
     * 获取缓存数据,如果关键字不存在返回null
     *
     * @param key
     * @return
     */
    Object get(String key);
    /**
     * 关闭
     */
    void shutdown();
}
