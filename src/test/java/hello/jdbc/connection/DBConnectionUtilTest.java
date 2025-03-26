package hello.jdbc.connection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.Connection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DBConnectionUtilTest {

    // 실행 전 해당 DB가 접속상태여야 오류 안난다
    // Connection : 자바 표준 DB 연동 드라이버 관리 인터페이스, 하위에 H2/MySQL/Oracle 종류별 드라이버 객체 구현, getConnection() 통해 연동
    @Test
    void connection() {
        Connection connection = DBConnectionUtil.getConnection();
        assertThat(connection).isNotNull();
    }
}