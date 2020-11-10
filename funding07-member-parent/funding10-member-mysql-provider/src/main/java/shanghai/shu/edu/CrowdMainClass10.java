package shanghai.shu.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("shanghai.shu.edu.mapper")
public class CrowdMainClass10 {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass10.class,args);
    }
}
