
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import shanghai.shu.edu.CrowdMainClass10;
import shanghai.shu.edu.entity.po.Member;
import shanghai.shu.edu.mapper.MemberMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CrowdMainClass10.class)
public class myBatisTest {
    private Logger logger=LoggerFactory.getLogger(myBatisTest.class);
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberMapper memberMapper;
    @Test
    public void testMapper(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String source="123123";
        String encode = passwordEncoder.encode(source);
        Member member = new Member(null, "jack", encode, "杰克", "jack@qq.com", 1, 1, "杰克", "123123", 2);
        memberMapper.insert(member);
    }
    @Test
    public void testConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        logger.debug(conn.toString());
    }
}
