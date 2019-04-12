/*
 * Copyright (C) 2019 BlueMiaomiao
 * FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x) may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 */

package com.bluemiaomiao.autoconfiguration;

import com.bluemiaomiao.properties.FastdfsProperties;
import com.bluemiaomiao.service.FastdfsClientService;
import org.csource.common.FastdfsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(FastdfsProperties.class)
public class FastdfsAutoConfiguration {
    @Autowired
    private FastdfsProperties fastdfsProperties;

    @Bean
    @ConditionalOnMissingBean(FastdfsClientService.class)
    public FastdfsClientService fastdfsClientService() throws IOException, FastdfsException {
        return new FastdfsClientService(fastdfsProperties);
    }
}