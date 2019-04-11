/*
 * Copyright (C) 2019 BlueMiaomiao
 * FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x) may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 */

package com.bluemiaomiao.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fastdfs")
public class FastdfsProperties {

    private String connect_timeout = "5";
    private String network_timeout = "30";
    private String charset = "UTF-8";
    private String http_anti_steal_token = "false";
    private String http_secret_key = "FastDFS1234567890";
    private String http_tracker_http_port = "80";
    private String tracker_server = "192.168.80.3:22122";

    public String getConnectTimeout() {
        return connect_timeout;
    }

    public String getNetworkTimeout() {
        return network_timeout;
    }

    public String getCharset() {
        return charset;
    }

    public String getHttpAntiStealToken() {
        return http_anti_steal_token;
    }

    public String getHttpSecretKey() {
        return http_secret_key;
    }

    public String getHttpTrackerHttpPort() {
        return http_tracker_http_port;
    }

    public String getTrackerServer() {
        return tracker_server;
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connect_timeout = connectTimeout;
    }

    public void setNetworkTimeout(String networkTimeout) {
        this.network_timeout = networkTimeout;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setHttpAntiStealToken(String httpAntiStealToken) {
        this.http_anti_steal_token = httpAntiStealToken;
    }

    public void setHttpSecretKey(String httpSecretKey) {
        this.http_secret_key = httpSecretKey;
    }

    public void setHttpTrackerHttpPort(String httpTrackerHttpPort) {
        this.http_tracker_http_port = httpTrackerHttpPort;
    }

    public void setTrackerServer(String trackerServer) {
        this.tracker_server = trackerServer;
    }
}
