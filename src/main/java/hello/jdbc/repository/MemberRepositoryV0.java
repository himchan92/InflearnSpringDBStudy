package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

// JDBC- DriverManager 사용
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "INSERT INTO MEMBER(MEMBER_ID, MONEY) VALUES (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            // SQL 파라미터 바인딩
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate(); // 변경 DB SQL 실행, 수행때 마다 숫자 반환(1: 수행, 1++: 재수행마다~)

            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            //항상 finally 타는부분에 예외처리 호출하여 방지
            //호출 안할시 DB 반납이 안되니 주의
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery(); // executeQuery() : 조회 SQL 반영

            //조회건수 있으면
            if(rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));

                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            // SQL 파라미터 바인딩
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate(); // 변경 DB SQL 실행, 성공 시 1
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            //항상 finally 타는부분에 예외처리 호출하여 방지
            //호출 안할시 DB 반납이 안되니 주의
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet set) {
        //리소스 반환은 역순으로 해야 한다
        //ResultSet : 조회 수행 시 반환
        if(set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
