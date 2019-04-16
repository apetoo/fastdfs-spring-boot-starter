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
        StorageClient client = this.pool.borrowObject();
        final String[] strings = client.upload_file(localFileName, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        final String[] strings = client.upload_file(localFileName, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    // public String[] upload(String groupName, String localFileName, String fileExtName, NameValuePair[] metadata) throws Exception{
    //     StorageClient client = this.pool.borrowObject();
    //     String[] strings = client.upload_file(groupName, localFileName, fileExtName, metadata);
    //     this.pool.returnObject(client);
    //     return strings;
    // }

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
    // public String[] upload(String groupName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
    //     return null;
    // }

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
    // public String[] upload(byte command, String groupName, String localFileName, String fileExtName, NameValuePair[] metadata) {
    //     return null;
    // }

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
    // public String[] upload(byte command, String groupName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
    //     return null;
    // }

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
    public String[] upload(byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) throws Exception {
        StorageClient client = this.pool.borrowObject();
        String[] strings = client.upload_file(buff, offset, len, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        String[] strings = client.upload_file(buff, offset, len, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) throws Exception {
        StorageClient client = this.pool.borrowObject();
        String[] strings = client.upload_file(groupName, buff, offset, len, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        String[] strings = client.upload_file(groupName, buff, offset, len, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
    }

    /**
     * 通过字节数组上传
     *
     * @param fileBuff    文件的字节数组
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] upload(byte[] fileBuff, String fileExtName, NameValuePair[] metadata) throws Exception {
        StorageClient client = this.pool.borrowObject();
        String[] strings = client.upload_file(fileBuff, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(byte[] fileBuff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        String[] strings = client.upload_file(fileBuff, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, byte[] fileBuff, String fileExtName, NameValuePair[] metadata) throws Exception {
        StorageClient client = this.pool.borrowObject();
        String[] strings = client.upload_file(groupName, fileBuff, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, byte[] fileBuff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        String[] strings = client.upload_file(groupName, fileBuff, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata) throws Exception {
        StorageClient client = this.pool.borrowObject();
        String[] strings = client.upload_file(groupName, fileSize, callback, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        String[] strings = client.upload_file(groupName, fileSize, callback, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, String masterFileName, String prefixName, String localFileName, String fileExtName, NameValuePair[] metadata) throws Exception {
        StorageClient client = this.pool.borrowObject();
        String[] strings = client.upload_file(groupName, masterFileName, prefixName, localFileName, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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
    public String[] upload(String groupName, String masterFileName, String prefixName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) throws Exception {
        StorageClient client = this.pool.borrowObject(waitTimeMillis);
        String[] strings = client.upload_file(groupName, masterFileName, prefixName, localFileName, fileExtName, metadata);
        this.pool.returnObject(client);
        return strings;
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

    /**
     * 将本地附加文件上传
     *
     * @param localFileName 本地文件名称
     * @param fileExtName   文件扩展名
     * @param metadata      元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String localFileName, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 将附加文件上传
     *
     * @param localFileName  文件名称
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 获取连接等待时长
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过本地上传附加文件
     *
     * @param groupName     组名
     * @param localFileName 本地文件名称
     * @param fileExtName   扩展名
     * @param metadata      元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, String localFileName, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过本地上传附加文件
     *
     * @param groupName      组名
     * @param localFileName  本地文件名称
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待获取连接的时长
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, String localFileName, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param buff        字节数组
     * @param offset      偏移量
     * @param len         长度
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param buff           字节数组
     * @param offset         偏移量
     * @param len            长度
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待获取连接的时长
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param groupName   组名
     * @param buff        字节数组
     * @param offset      偏移量
     * @param len         长度
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param groupName      组名
     * @param buff           字节数组
     * @param offset         偏移量
     * @param len            长度
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 获取连接的等待时长
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, byte[] buff, int offset, int len, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param buff        字节数组
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(byte[] buff, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param buff           字节数组
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待获取连接的时长
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(byte[] buff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过字节数组上传
     *
     * @param groupName   组名
     * @param buff        字节数组
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, byte[] buff, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过字节数组上传附加文件
     *
     * @param groupName      组名
     * @param buff           字节数组
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 获取连接的等待时长
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, byte[] buff, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 通过回调上传附加文件
     *
     * @param groupName   组名
     * @param fileSize    文件大小
     * @param callback    回调函数
     * @param fileExtName 扩展名
     * @param metadata    元数据
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata) {
        return null;
    }

    /**
     * 通过回调上传附加文件
     *
     * @param groupName      组名
     * @param fileSize       文件大小
     * @param callback       回调函数
     * @param fileExtName    扩展名
     * @param metadata       元数据
     * @param waitTimeMillis 等待时间
     * @return 文件组名和ID
     */
    public String[] uploadAppenderFile(String groupName, long fileSize, UploadCallback callback, String fileExtName, NameValuePair[] metadata, long waitTimeMillis) {
        return null;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param localFileName    本地文件名称
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, String localFileName) {
        return 0;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param localFileName    本地文件名称
     * @param waitTimeMillis   等待时间时长
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, String localFileName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param buff             文件字节数组
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, byte[] buff) {
        return 0;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param buff             文件字节数组
     * @param waitTimeMillis   等待时长
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, byte[] buff, long waitTimeMillis) {
        return 0;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param buff             字节数组
     * @param offset           偏移量
     * @param len              长度
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, byte[] buff, int offset, int len) {
        return 0;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param buff             字节数组
     * @param offset           偏移量
     * @param len              长度
     * @param waitTimeMillis   等待时长
     * @return 文件组名和ID
     */
    public int appendFile(String groupName, String appenderFileName, byte[] buff, int offset, int len, long waitTimeMillis) {
        return 0;
    }

    /**
     * 追加文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileSize         文件大小
     * @param callback         回调函数
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, long fileSize, UploadCallback callback) {
        return 0;
    }

    /**
     * 上传文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileSize         文件大小
     * @param callback         回调函数
     * @param waitTimeMillis   等待时间
     * @return 返回0表示成功
     */
    public int appendFile(String groupName, String appenderFileName, long fileSize, UploadCallback callback, long waitTimeMillis) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param localFileName    本地文件名称
     * @return 返回0表示成功
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, String localFileName) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param localFileName    本地文件名称
     * @param waitTimeMillis   等待时长
     * @return 返回0表示成功
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, String localFileName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param buff             字节数组
     * @return 返回0表示成功
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, byte[] buff) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param buff             字节数组
     * @param waitTimeMillis   等待时间
     * @return 返回0表示成功
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, byte[] buff, long waitTimeMillis) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param buff             字节数组
     * @param bufferOffset     偏移量
     * @param bufferLen        长度
     * @return 返回0表示成功
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, byte[] buff, int bufferOffset, int bufferLen) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       文件偏移量
     * @param buff             字节数组
     * @param bufferOffset     字节偏移量
     * @param bufferLen        字节长度
     * @param waitTimeMillis   等待时间
     * @return 返回0表示成功
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, byte[] buff, int bufferOffset, int bufferLen, long waitTimeMillis) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param modifySize       修改大小
     * @param callback         回调函数
     * @return 文件组名和ID
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, long modifySize, UploadCallback callback) {
        return 0;
    }

    /**
     * 修改文件
     *
     * @param groupName        组名
     * @param appenderFileName 追加文件名称
     * @param fileOffset       偏移量
     * @param modifySize       修改大小
     * @param callback         回调函数
     * @param waitTimeMillis   等待时间
     * @return 文件组名和ID
     */
    public int modify(String groupName, String appenderFileName, long fileOffset, long modifySize, UploadCallback callback, long waitTimeMillis) {
        return 0;
    }

    /**
     * 删除文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @return 返回0表示成功
     */
    public int delete(String groupName, String remoteFileName) {
        return 0;
    }

    /**
     * 删除文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public int delete(String groupName, String remoteFileName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 文件分片
     *
     * @param groupName    组名
     * @param appenderName 输出源文件名称
     * @return 返回0表示成功
     */
    public int truncate(String groupName, String appenderName) {
        return 0;
    }

    /**
     * 文件分片
     *
     * @param groupName      组名
     * @param appenderName   输出源文件名称
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public int truncate(String groupName, String appenderName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 文件分片
     *
     * @param truncatedFileSize 分片大小
     * @param groupName         组名
     * @param appenderName      输出源文件名称
     * @return 返回0表示成功
     */
    public int truncate(long truncatedFileSize, String groupName, String appenderName) {
        return 0;
    }

    /**
     * 文件分片
     *
     * @param truncatedFileSize 分片大小
     * @param groupName         组名
     * @param appenderName      输出源文件
     * @param waitTimeMillis    等待时间
     * @return 返回0表示成功
     */
    public int truncate(long truncatedFileSize, String groupName, String appenderName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @return 返回0表示成功
     */
    public byte[] download(String groupName, String remoteFileName) {
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public byte[] download(String groupName, String remoteFileName, long waitTimeMillis) {
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param offset         偏移量
     * @param bytes          字节数组
     * @return 返回0表示成功
     */
    public byte[] download(String groupName, String remoteFileName, long offset, long bytes) {
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param offset         偏移量
     * @param bytes          字节数组
     * @param waitTimeMillis 等待时长
     * @return 返回0表示成功
     */
    public byte[] download(String groupName, String remoteFileName, long offset, long bytes, long waitTimeMillis) {
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param localFileName  本地文件名称
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, String localFileName) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param localFileName  本地文件名称
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, String localFileName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param offset         偏移量
     * @param bytes          字节数组
     * @param localFileName  本地文件名称
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, long offset, long bytes, String localFileName) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param offset         偏移量
     * @param bytes          字节数组
     * @param localFileName  本地文件名称
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, long offset, long bytes, String localFileName, long waitTimeMillis) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param callback       回调函数
     * @return 文件分组和ID
     */
    public int download(String groupName, String remoteFileName, DownloadCallback callback) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param callback       回调函数
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, DownloadCallback callback, long waitTimeMillis) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param offset         偏移量
     * @param bytes          字节数组
     * @param callback       回调函数
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, long offset, long bytes, DownloadCallback callback) {
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param offset         偏移量
     * @param bytes          字节数组
     * @param callback       回调函数
     * @param waitTimeMillis 等待时间
     * @return 返回0表示成功
     */
    public int download(String groupName, String remoteFileName, long offset, long bytes, DownloadCallback callback, long waitTimeMillis) {
        return 0;
    }

    /**
     * 获取元数据
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @return 键值对数组
     */
    public NameValuePair[] getMetadata(String groupName, String remoteFileName) {
        return null;
    }

    /**
     * 获取元数据
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param waitTimeMillis 等待时间
     * @return 键值对数组
     */
    public NameValuePair[] getMetadata(String groupName, String remoteFileName, long waitTimeMillis) {
        return null;
    }

    /**
     * 设置元数据
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param metadata       元数据
     * @param flag           更新设置：ProtoCommon.STORAGE_SET_METADATA_FLAG_OVERWRITE 表示重写所有; ProtoCommon.STORAGE_SET_METADATA_FLAG_MERGE 表示没有就插入，有就更新
     * @return 返回0表示成功
     */
    public int setMetadata(String groupName, String remoteFileName, NameValuePair[] metadata, byte flag) {
        return 0;
    }

    /**
     * 设置元数据
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param metadata       元数据
     * @param flag           更新设置：ProtoCommon.STORAGE_SET_METADATA_FLAG_OVERWRITE 表示重写所有; ProtoCommon.STORAGE_SET_METADATA_FLAG_MERGE 表示没有就插入，有就更新
     * @param waitTimeMillis 等待时长
     * @return 返回0表示成功
     */
    public int setMetadata(String groupName, String remoteFileName, NameValuePair[] metadata, byte flag, long waitTimeMillis) {
        return 0;
    }

    /**
     * 获取文件信息
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @return 返回文件信息
     */
    public FileInfo getFileInfo(String groupName, String remoteFileName) {
        return null;
    }

    /**
     * 获取文件名称
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件名称
     * @param waitTimeMillis 等待时长
     * @return 返回文件信息
     */
    public FileInfo getFileInfo(String groupName, String remoteFileName, long waitTimeMillis) {
        return null;
    }

    /**
     * 查询文件信息
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件信息
     * @return 返回文件信息
     */
    public FileInfo queryFileInfo(String groupName, String remoteFileName) {
        return null;
    }

    /**
     * 查询文件信息
     *
     * @param groupName      组名
     * @param remoteFileName 远程文件信息
     * @param waitTimeMillis 等待时间
     * @return 返回文件信息
     */
    public FileInfo queryFileInfo(String groupName, String remoteFileName, long waitTimeMillis) {
        return null;
    }
}
