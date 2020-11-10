package shanghai.shu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class CrowdMainClass16 {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass16.class,args);
    }
}
