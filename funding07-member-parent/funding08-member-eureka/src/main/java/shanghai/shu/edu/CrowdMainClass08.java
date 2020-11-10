package shanghai.shu.edu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class CrowdMainClass08 {

    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass08.class, args);
    }

}
