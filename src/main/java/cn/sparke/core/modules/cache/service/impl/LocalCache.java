package cn.sparke.core.modules.cache.service.impl;


import cn.sparke.core.modules.cache.bean.CacheObj;
import cn.sparke.core.modules.cache.service.CacheService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangbowen on 2016/4/5.
 */
public class LocalCache implements CacheService {
    // 缓存
    private Map<String, Object> maps = null;

    public LocalCache() {
        maps = new ConcurrentHashMap<>();
    }

    @Override
    public void set(String key, Object value, int exp) {
        CacheObj cacheObj = new CacheObj();
        cacheObj.setObj(value);
        cacheObj.setSaveTime(System.currentTimeMillis());
        cacheObj.setExp(exp);
        maps.put(key, cacheObj);
    }

    /*
             * 删除缓存数据
             */
    @Override
    public void delete(String key) {
        maps.remove(key);
    }

    /*
     * 获取缓存数据,如果关键字不存在返回null
     */
    @Override
    public Object get(String key) {
        CacheObj cacheObj = (CacheObj) maps.get(key);
        //说明没过期
        if (cacheObj != null && (cacheObj.getExp() == 0 || ((System.currentTimeMillis() - cacheObj.getSaveTime()) <= cacheObj.getExp() * 1000))) {
            return cacheObj.getObj();
        }
        return null;
    }

    @Override
    public void shutdown() {
    }
}
