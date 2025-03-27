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

    기존 자바 예외처리
    - 체크예외 : 예외를 던지는 throws 처리 필수이며 안하면 오류
    - 언체크예외 : throws 없이도 예외처리(실무에서 자주사용)
    - Exception : 최상위 예외로 모든 예외 컴파일러가 체크
    - throws Exception : 하위 예외모두 던지기 가능
    - 예외처리 안할 시 시스템에서 오류를 안잡고 바로 종료 시켜버림
    - 메소드 throws : 메소드밖으로 예외처리를 던짐
    - 장점 : 개발자의 실수로 누락된 예외처리를 시스템이 잡아서 예외체크
    - 단점 : 개발자가 예외를 잡거나 던지도록 로직 처리 필요
    - 실무에서 예외 메세지는 로그를 주로 사용하여 권장
       - e.printStackTrace() : 비추천
         log("e: " + e);     : 추천
 */
@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

}