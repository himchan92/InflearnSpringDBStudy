package hello.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtil {
    // DB 연동 객체
    public static Connection getConnection() {
        try {
            // 기존 DataSource 는 호출마다 연동작업이 수행되어 메모리 낭비 심함
            // 보완으로 HikariCP 사용 : 스프링부트는 내장되어 자동지원, 호출때 한번만 수행, 설정과 사용을 분리
            Connection connection = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
