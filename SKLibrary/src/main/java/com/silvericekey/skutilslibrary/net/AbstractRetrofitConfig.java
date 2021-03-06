package com.silvericekey.skutilslibrary.net;

import java.net.Proxy;

/**
 * retrofit配置接口
 */
public abstract class AbstractRetrofitConfig {
    /**
     * 默认连接超时
     */
    public static final Long DEFAULT_CONNECT_TIMEOUT = 10 * 1000L;
    /**
     * 默认读取超时
     */
    public static final Long DEFAULT_READ_TIMEOUT = 10 * 1000L;
    /**
     * 默认写入超时
     */
    public static final Long DEFAULT_WRITE_TIMEOUT = 10 * 1000L;

    /**
     * 获取基础host
     *
     * @return
     */
    public abstract String getBaseUrl();

    /**
     * 获取连接超时时间
     *
     * @return
     */
    public Long connectTimeout() {
        return DEFAULT_CONNECT_TIMEOUT;
    }

    /**
     * 获取读取超时时间
     *
     * @return
     */
    public Long readTimeout() {
        return DEFAULT_READ_TIMEOUT;
    }

    /**
     * 获取写入超时时间
     *
     * @return
     */
    public Long writeTimeout() {
        return DEFAULT_WRITE_TIMEOUT;
    }

    /**
     * 获取代理ip
     *
     * @return
     */
    public String proxyIPAddr() {
        return "";
    }

    /**
     * 获取代理端口
     *
     * @return
     */
    public int proxyPort() {
        return 0;
    }

    /**
     * 获取代理类型
     *
     * @return
     */
    public Proxy.Type proxyType() {
        return Proxy.Type.DIRECT;
    }

    /**
     * 代理用户名
     * @return
     */
    public String proxyUserName(){
        return "";
    }

    /**
     * 代理密码
     * @return
     */
    public String proxyPassword(){
        return "";
    }
}
