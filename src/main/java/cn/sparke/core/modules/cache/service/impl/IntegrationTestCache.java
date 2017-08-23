package cn.sparke.core.modules.cache.service.impl;

import cn.sparke.core.modules.cache.service.CacheService;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by zhangbowen on 2017/7/14.
 */
public class IntegrationTestCache implements CacheService {
    // OCS客户端
    private MemcachedClient mc = null;

    public IntegrationTestCache(String url, int port) {
        try {
            mc = new MemcachedClient(new InetSocketAddress(url, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 添加缓存数据
     */
    @Override
    public void set(String key, Object value, int exp) {
        mc.set(key, exp, value);
    }

    @Override
    public void delete(String key) {
        mc.delete(key);
    }

    /*
     * 获取缓存数据,如果关键字不存在返回null
     */
    @Override
    public Object get(String key) {
        return mc.get(key);
    }

    @Override
    public void shutdown() {
        mc.shutdown();
    }
}
