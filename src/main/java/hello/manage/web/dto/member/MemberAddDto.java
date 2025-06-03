package hello.manage.web.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAddDto {
    /**
     * 회원 가입에 사용되는 dto
     */
    private String username;
    private String password;
    private String email;
}
