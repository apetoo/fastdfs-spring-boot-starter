# fastdfs-spring-boot-starter

一个同时兼容SpringBoot1.x和2.x的高性能FastDFS客户端.

* 自动添加依赖

* 初始化配置项

* 基于Commons Pool2 实现的高性能连接池

* 更多操作FastDFS的API（正在实现）

# 快速开始

* 下载.

    ```bash
    git clone https://github.com/bluemiaomiao/fastdfs-spring-boot-starter.git
    cd fastdfs-spring-boot-starter
    ```

* 安装到本地仓库.

    ```bash
    mvn clean install
    ```
    
* 添加到项目.

    ```xml
    <dependency>
        <groupId>com.bluemiaomiao</groupId>
        <artifactId>fastdfs-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    ```

* 在主配置类上添加注解 (``@EnableFastdfsClient``).

    ```java
    @EnableFastdfsClient
    @SpringBootApplication
    public class DemoApplication {
    
        @Autowired
        private FastdfsClientService fastdfsClientService;
    
        public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }
    }
    ```
* 添加配置条目(application.properties).

    ```properties
    fastdfs.connect-timeout=5
    fastdfs.network-timeout=30
    fastdfs.charset=UTF-8
    fastdfs.http-tracker-http-port=8000
    fastdfs.tracker-server=192.168.80.3:22122
    fastdfs.http-secret-key=lzsdfbgdiuxfbKFGKUygboYFOUYDgi
    fastdfs.http-anti-steal-token=true
    ```

* 添加配置条目(application.yml).

    ```yml
    fastdfs:
      tracker-server: 192.168.80.3:22122
      http-tracker-http-port: 8000
      http-anti-steal-token: true
      network-timeout: 5
      http-secret-key: LKUGDuyfgUyfgouiygFVuyfoUFVIOuyf
      connect-timeout: 5
      charset: UTF-8
    ```
    
* 即刻享受它带来的便利.