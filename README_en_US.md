# fastdfs-spring-boot-starter

FastDFS Java Client(for SpringBoot1.x & SpringBoot 2.x).

* Import dependence

* Initial configuration

* Connection pool

* More method(in process)

# Quick start

* Download.

    ```bash
    git clone https://github.com/bluemiaomiao/fastdfs-spring-boot-starter.git
    cd fastdfs-spring-boot-starter
    ```

* Install to local repository.

    ```bash
    mvn clean install
    ```
    
* Add to project.

    ```xml
    <dependency>
        <groupId>com.bluemiaomiao</groupId>
        <artifactId>fastdfs-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    ```

* Add annotations and service (``@EnableFastdfsClient``).

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
* Add configuration entries(application.properties).

    ```properties
    fastdfs.connect-timeout=5
    fastdfs.network-timeout=30
    fastdfs.charset=UTF-8
    fastdfs.http-tracker-http-port=8000
    fastdfs.tracker-server=192.168.80.3:22122
    fastdfs.http-secret-key=lzsdfbgdiuxfbKFGKUygboYFOUYDgi
    fastdfs.http-anti-steal-token=true
    ```

* Add configuration entries(application.yml).

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
    
* Enjoy it.