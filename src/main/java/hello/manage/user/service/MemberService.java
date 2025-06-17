package hello.manage.user.service;

import hello.manage.user.domain.Member;
import hello.manage.user.web.dto.MemberAddDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Member save(MemberAddDto memberAddDto);
}
