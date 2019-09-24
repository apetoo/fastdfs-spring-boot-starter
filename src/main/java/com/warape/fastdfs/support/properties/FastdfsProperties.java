/*
 * Copyright (C) 2019 BlueMiaomiao
 * FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x) may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 */

package com.warape.fastdfs.support.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fastdfs")
public class FastdfsProperties {
    /**
     * 连接超时时间
     */
    private String connectTimeout = "5";
    /**
     * 网络超时时间
     */
    private String networkTimeout = "30";
    /**
     * 字符集编码
     */
    private String charset = "UTF-8";
    /**
     * 是否使用Token
     */
    private String httpAntiStealToken = "false";
    /**
     * Token加密密钥
     */
    private String httpSecretKey = "";
    /**
     * 跟踪器IP地址，多个使用分号隔开
     */
    private String httpTrackerHttpPort = "";
    /**
     * 连接池的连接对象最大个数
     */
    private String trackerServers = "";
    /**
     * 连接池的最大空闲对象个数
     */
    private String connectionPoolMaxTotal = "18";
    /**
     * 连接池的最小空闲对象个数
     */
    private String connectionPoolMaxIdle = "18";
    /**
     * Nginx服务器IP，多个使用分号分割
     */
    private String connectionPoolMinIdle = "2";
    /**
     * 获取连接对象时可忍受的等待时长(毫秒)
     */
    private String nginxServers = "";

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setNetworkTimeout(String networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setHttpAntiStealToken(String httpAntiStealToken) {
        this.httpAntiStealToken = httpAntiStealToken;
    }

    public void setHttpSecretKey(String httpSecretKey) {
        this.httpSecretKey = httpSecretKey;
    }

    public void setHttpTrackerHttpPort(String httpTrackerHttpPort) {
        this.httpTrackerHttpPort = httpTrackerHttpPort;
    }

    public void setTrackerServers(String trackerServers) {
        this.trackerServers = trackerServers;
    }

    public void setConnectionPoolMaxTotal(String connectionPoolMaxTotal) {
        this.connectionPoolMaxTotal = connectionPoolMaxTotal;
    }

    public void setConnectionPoolMaxIdle(String connectionPoolMaxIdle) {
        this.connectionPoolMaxIdle = connectionPoolMaxIdle;
    }

    public void setConnectionPoolMinIdle(String connectionPoolMinIdle) {
        this.connectionPoolMinIdle = connectionPoolMinIdle;
    }

    public void setNginxServers(String nginxServers) {
        this.nginxServers = nginxServers;
    }

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public String getNetworkTimeout() {
        return networkTimeout;
    }

    public String getCharset() {
        return charset;
    }

    public String getHttpAntiStealToken() {
        return httpAntiStealToken;
    }

    public String getHttpSecretKey() {
        return httpSecretKey;
    }

    public String getHttpTrackerHttpPort() {
        return httpTrackerHttpPort;
    }

    public String getTrackerServers() {
        return trackerServers;
    }

    public String getConnectionPoolMaxTotal() {
        return connectionPoolMaxTotal;
    }

    public String getConnectionPoolMaxIdle() {
        return connectionPoolMaxIdle;
    }

    public String getConnectionPoolMinIdle() {
        return connectionPoolMinIdle;
    }

    public String getNginxServers() {
        return nginxServers;
    }
}