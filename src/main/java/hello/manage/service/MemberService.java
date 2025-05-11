package hello.manage.service;

import hello.manage.domain.Member;
import hello.manage.dto.member.MemberAddDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Member save(MemberAddDto memberAddDto);
}
