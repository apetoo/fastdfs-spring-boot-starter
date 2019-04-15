/*
 * Copyright (C) 2019 BlueMiaomiao
 * FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x) may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 */

package com.bluemiaomiao.service;

import com.bluemiaomiao.factory.StorageClientFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import com.bluemiaomiao.properties.FastdfsProperties;

import java.util.Properties;

// 实现了常用的方法
// 包括：上传文件/下载文件/删除文件/防盗链
public class FastdfsClientService {

    // SpringBoot加载的配置文件
    // 连接池配置项
    // 转换后的配置条目
    // 连接池
    // Nginx服务器
    private FastdfsProperties fdfsProp;
    private GenericObjectPoolConfig config;
    private Properties prop;
    private GenericObjectPool<StorageClient> pool;
    private String[] nginxServers;

    public FastdfsClientService(FastdfsProperties fdfsProp) throws Exception {
        this.fdfsProp = fdfsProp;
        init();
        create();
    }

    // 初始化全局客户端
    private void init() throws Exception {
        this.prop = new Properties();
        this.prop.put("fastdfs.connect_timeout_in_seconds", this.fdfsProp.getConnect_timeout());
        this.prop.put("fastdfs.network_timeout_in_seconds", this.fdfsProp.getNetwork_timeout());
        this.prop.put("fastdfs.charset", this.fdfsProp.getCharset());
        this.prop.put("fastdfs.http_anti_steal_token", this.fdfsProp.getHttp_anti_steal_token());
        this.prop.put("fastdfs.http_secret_key", this.fdfsProp.getHttp_secret_key());
        this.prop.put("fastdfs.http_tracker_http_port", this.fdfsProp.getHttp_tracker_http_port());
        this.prop.put("fastdfs.tracker_servers", this.fdfsProp.getTracker_server());
        this.prop.put("fastdfs.connection_pool_max_total", this.fdfsProp.getConnection_pool_max_total());
        this.prop.put("fastdfs.connection_pool_max_idle", this.fdfsProp.getConnection_pool_max_idle());
        this.prop.put("fastdfs.connection_pool_min_idle", this.fdfsProp.getConnection_pool_min_idle());
        this.prop.put("fastdfs.nginx_server", this.fdfsProp.getNginx_server());
        ClientGlobal.initByProperties(this.prop);
    }

    // 显示初始化信息
    private void info() {

    }

    // 初始化并创建连接池
    private void create() {
        this.config = new GenericObjectPoolConfig();
        this.config.setMaxTotal(Integer.parseInt(this.prop.getProperty("fastdfs.connection_pool_max_totol")));
        this.config.setMaxIdle(Integer.parseInt(this.prop.getProperty("fastdfs.connection_pool_max_idle")));
        this.config.setMinIdle(Integer.parseInt(this.prop.getProperty("fastdfs.connection_pool_min_idle")));
        StorageClientFactory factory = new StorageClientFactory();
        this.pool = new GenericObjectPool<StorageClient>(factory, this.config);
        this.nginxServers = this.prop.getProperty("fastdfs.nginx_server").split(";");
    }


    /**
     * 通过本地文件上传
     *
     * @param localFileName 本地文件名称
     * @param fileExtName   文件扩展名
     * @param metadata      文件的元数据
     * @return 文件组名和ID
     */
    public String[] upload(String localFileName, String fileExtName, NameValuePair[] metadata) throws Exception {
        return null;
    }

    /**
     * 通过本地文件上传
     *
     * @param localFileName  本地文件名称
     * @param fileExtName    文件扩展名
     * @param metadata       文件的元数据
     * @param waitTimeMillis 获取连接等待时间
     * @return 文件组名和ID
     */
    public String[] upload(String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过本地文件上传
     *
     * @param groupName     组名
     * @param localFileName 本地文件名称
     * @param fileExtName   文件扩展名
     * @param metadata      文件的元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String localFileName, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过本地文件上传
     *
     * @param groupName      组名
     * @param localFileName  本地文件名称
     * @param fileExtName    文件扩展名
     * @param metadata       文件的元数据
     * @param waitTimeMillis 获取连接等待时间
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过本地文件上传
     *
     * @param command       指令
     * @param groupName     组名
     * @param localFileName 本地文件名称
     * @param fileExtName   文件扩展名
     * @param metadata      文件的元数据
     * @return 文件组名和ID
     */
    public String[] upload(byte command, String groupName, String localFileName, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过本地文件上传
     *
     * @param command        指令
     * @param groupName      组名
     * @param localFileName  本地文件名称
     * @param fileExtName    文件扩展名
     * @param metadata       文件的元数据
     * @param waitTimeMillis 获取连接的等待时间
     * @return 文件组名和ID
     */
    public String[] upload(byte command, String groupName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param buff        文件的字节数组
     * @param offset      偏移量
     * @param len         长度
     * @param fileExtName 文件扩展名
     * @param metadata    文件的元数据
     * @return 文件组名和ID
     */
    public String[] upload(byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param buff           文件的字节数组
     * @param offset         偏移量
     * @param len            长度
     * @param fileExtName    文件扩展名
     * @param metadata       文件的元数据
     * @param waitTimeMillis 获取连接的等待时间
     * @return 文件组名和ID
     */
    public String[] upload(byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传
     *
     * @param groupName   文件的组名
     * @param buff        文件的字节数组
     * @param offset      偏移量
     * @param len         长度
     * @param fileExtName 文件扩展名
     * @param metadata    文件的元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param groupName      文件的组名
     * @param buff           文件的字节数组
     * @param offset         偏移量
     * @param len            长度
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 获取连接的等待时间
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传
     *
     * @param fileBuff    文件的字节数组
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] upload(byte[] fileBuff, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param fileBuff       文件的字节数组
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 获取连接的等待时间
     * @return 文件组名和ID
     */
    public String[] upload(byte[] fileBuff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param groupName   文件组名
     * @param fileBuff    文件的字节数组
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件的组名和ID
     */
    public String[] upload(String groupName, byte[] fileBuff, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param groupName      文件组名
     * @param fileBuff       文件的字节数组
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待连接的时长
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, byte[] fileBuff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过回调接口上传文件
     *
     * @param groupName   文件组名
     * @param fileSize    文件大小
     * @param callback    回调函数
     * @param fileExtName 文件扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过回调接口上传文件
     *
     * @param groupName      文件组名
     * @param fileSize       文件大小
     * @param callback       回调接口
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待连接的时长
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过本地上传文件
     *
     * @param groupName      文件组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param localFileName  本地文件名称
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, String localFileName, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过本地上传文件
     *
     * @param groupName      文件组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param localFileName  本地文件名称
     * @param fileExtName    文件扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待连接的时长
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传
     *
     * @param groupName      文件组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param buff           文件的字节数组
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, byte[] buff, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传
     *
     * @param groupName      组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param buff           文件的字节数组
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待连接的时长
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, byte[] buff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param groupName      组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param buff           文件的字节数组
     * @param offset         偏移量
     * @param len            长度
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传文件
     *
     * @param groupName      组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param buff           文件的字节数组
     * @param offset         偏移量
     * @param len            长度
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待连接的时长
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过回调函数上传文件
     *
     * @param groupName      组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param fileSize       文件的大小
     * @param callback       回调接口
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过回调函数上传文件
     *
     * @param groupName      组名
     * @param masterFileName 生成副本的文件名
     * @param prefixName     生成副本的文件名前缀
     * @param fileSize       文件的大小
     * @param callback       回调接口
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待连接的时长
     * @return 文件组名和ID
     */
    public String[] upload(String groupName, String masterFileName, String prefixName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }
}
