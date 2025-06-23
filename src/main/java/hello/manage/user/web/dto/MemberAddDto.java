package hello.manage.user.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAddDto {
    /**
     * 회원 가입에 사용되는 dto
     */
    private String memberName;
    private String password;
    private String email;
}
