package hello.jdbc.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hello.jdbc.domain.Member;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MemberRepositoryV0Test {
    
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException  {
        Member member = new Member("memberV7", 50000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member != findMember {}", member == findMember);
        Assertions.assertThat(findMember).isEqualTo(member);

        //update
        repository.update(member.getMemberId(), 50000);
        Member updateMember = repository.findById(member.getMemberId());
        assertThat(updateMember).isEqualTo(member);
    }
}