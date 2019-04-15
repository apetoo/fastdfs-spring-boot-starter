/*
 * Copyright (C) 2019 BlueMiaomiao
 * FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x) may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 */

package com.bluemiaomiao.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fastdfs")
public class FastdfsProperties {
    // 连接超时时间
    // 网络超时时间
    // 字符集编码
    // 是否使用Token
    // Token加密密钥
    // 跟踪器IP地址，多个使用分号隔开
    // 连接池的连接对象最大个数
    // 连接池的最大空闲对象个数
    // 连接池的最小空闲对象个数
    // Nginx服务器IP，多个使用分号分割
    // 获取连接对象时可忍受的等待时长(毫秒)
    private String connect_timeout = "5";
    private String network_timeout = "30";
    private String charset = "UTF-8";
    private String http_anti_steal_token = "false";
    private String http_secret_key = "FastDFS1234567890";
    private String http_tracker_http_port = "80";
    private String tracker_server = "192.168.80.3:22122";
    private String connection_pool_max_total = "18";
    private String connection_pool_max_idle = "18";
    private String connection_pool_min_idle = "2";
    private String nginx_server = "192.168.80.3:8000";

    public String getConnect_timeout() {
        return connect_timeout;
    }

    public String getNetwork_timeout() {
        return network_timeout;
    }

    public String getCharset() {
        return charset;
    }

    public String getHttp_anti_steal_token() {
        return http_anti_steal_token;
    }

    public String getHttp_secret_key() {
        return http_secret_key;
    }

    public String getHttp_tracker_http_port() {
        return http_tracker_http_port;
    }

    public String getTracker_server() {
        return tracker_server;
    }

    public String getConnection_pool_max_total() {
        return connection_pool_max_total;
    }

    public String getConnection_pool_max_idle() {
        return connection_pool_max_idle;
    }

    public String getConnection_pool_min_idle() {
        return connection_pool_min_idle;
    }

    public String getNginx_server() {
        return nginx_server;
    }

    public void setConnect_timeout(String connect_timeout) {
        this.connect_timeout = connect_timeout;
    }

    public void setNetwork_timeout(String network_timeout) {
        this.network_timeout = network_timeout;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setHttp_anti_steal_token(String http_anti_steal_token) {
        this.http_anti_steal_token = http_anti_steal_token;
    }

    public void setHttp_secret_key(String http_secret_key) {
        this.http_secret_key = http_secret_key;
    }

    public void setHttp_tracker_http_port(String http_tracker_http_port) {
        this.http_tracker_http_port = http_tracker_http_port;
    }

    public void setTracker_server(String tracker_server) {
        this.tracker_server = tracker_server;
    }

    public void setConnection_pool_max_total(String connection_pool_max_totol) {
        this.connection_pool_max_total = connection_pool_max_totol;
    }

    public void setConnection_pool_max_idle(String connection_pool_max_idle) {
        this.connection_pool_max_idle = connection_pool_max_idle;
    }

    public void setConnection_pool_min_idle(String connection_pool_min_idle) {
        this.connection_pool_min_idle = connection_pool_min_idle;
    }

    public void setNginx_server(String nginx_server) {
        this.nginx_server = nginx_server;
    }
}