/*
 * Copyright (C) 2019 BlueMiaomiao
 * FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x) may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 */

package com.bluemiaomiao.service;

import org.csource.common.FastdfsException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bluemiaomiao.properties.FastdfsProperties;

import java.io.IOException;
import java.util.Properties;

public class FastdfsClientService {

    public FastdfsClientService(FastdfsProperties fastdfsProperties) throws IOException, FastdfsException {
        super();
        // Convert to general Prop object.
        // Auto read the config item from application.properties.
        Properties properties = new Properties();
        properties.put("fastdfs.connect_timeout_in_seconds", fastdfsProperties.getConnectTimeout());
        properties.put("fastdfs.network_timeout_in_seconds", fastdfsProperties.getNetworkTimeout());
        properties.put("fastdfs.charset", fastdfsProperties.getCharset());
        properties.put("fastdfs.http_anti_steal_token", fastdfsProperties.getHttpAntiStealToken());
        properties.put("fastdfs.http_secret_key", fastdfsProperties.getHttpSecretKey());
        properties.put("fastdfs.http_tracker_http_port", fastdfsProperties.getHttpTrackerHttpPort());
        properties.put("fastdfs.tracker_servers", fastdfsProperties.getTrackerServer());

        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Reading config item：fastdfs.connect_timeout_in_seconds==> " + properties.getProperty(
                "fastdfs.connect_timeout_in_seconds"));
        logger.info("Reading config item：fastdfs.network_timeout_in_seconds==> " + properties.getProperty(
                "fastdfs.network_timeout_in_seconds"));
        logger.info("Reading config item：fastdfs.charset==> " + properties.getProperty(
                "fastdfs.charset"));
        logger.info("Reading config item：fastdfs.http_anti_steal_token==> " + properties.getProperty(
                "fastdfs.http_anti_steal_token"));
        logger.info("Reading config item：fastdfs.http_secret_key==> " + properties.getProperty(
                "fastdfs.http_secret_key"));
        logger.info("Reading config item：fastdfs.http_tracker_http_port==> " + properties.getProperty(
                "fastdfs.http_tracker_http_port"));
        logger.info("Reading config item：fastdfs.tracker_servers==> " + properties.getProperty(
                "fastdfs.tracker_servers"));
        ClientGlobal.initByProperties(properties);
    }

}
