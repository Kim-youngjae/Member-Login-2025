package hello.manage.user.service;

import hello.manage.user.domain.Member;
import hello.manage.user.web.dto.MemberAddDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    Member save(MemberAddDto memberAddDto);

    List<Member> findAll();
}
