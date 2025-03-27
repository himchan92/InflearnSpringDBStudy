package hello.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    DB 락?
    - 트랜젝션 원자성 깨지는 현상
    - 커밋 수행하지 않았는데 동시에 같은 데이터를 다른데서 수정 시 문제 발생
    - 커밋이나 롤백 전까지는 다른데서 수정못하게 막기위함
    - 락 대기 시간이 길어지면 타임아웃 오류 발생
    - 커밋 수행 후 트랜젝션 종료 시 락반납

    스프링 트랜젝션
    - @Transactionoal : 시작과 끝을 유지하려면 같은 DB 커넥션 유지를 위해 동기화 지원
 */
@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

}