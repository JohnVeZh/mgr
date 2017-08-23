package cn.sparke.core.modules.cache.service.impl;

import cn.sparke.core.modules.cache.service.CacheService;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

import java.io.IOException;

/**
 * Created by zhangbowen on 2016/4/5.
 * 阿里云memcached缓存
 */
public class AliMemCache implements CacheService {
    // OCS客户端
    private MemcachedClient mc = null;

    public AliMemCache(String uid, String pwd, String url){
        // 指定验证机制，推荐PLAIN，
        // 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
        AuthDescriptor ad = new AuthDescriptor(new String[]{"PLAIN"},
                new PlainCallbackHandler(uid, pwd)); // 用户名，密码

        try {
            mc = new MemcachedClient(new ConnectionFactoryBuilder()
                    .setProtocol(ConnectionFactoryBuilder.Protocol.BINARY) // 指定使用Binary协议
                    .setOpTimeout(1000)// 设置超时时间为100ms
                    .setAuthDescriptor(ad).build(), AddrUtil.getAddresses(url)); // 访问地址
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
