package hello.jdbc.domain;

import lombok.Data;

@Data // 롬복 제공함수 통합지원
public class Member {
    private String memberId;
    private int money;

    public Member() {}

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
