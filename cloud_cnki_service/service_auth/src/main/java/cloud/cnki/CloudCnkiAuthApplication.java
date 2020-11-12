package cloud.cnki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author dujingxin
 * @Description
 * @Date 11:25 2020/11/10
 * @Param
 * @return
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudCnkiAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudCnkiAuthApplication.class, args);
    }
}
