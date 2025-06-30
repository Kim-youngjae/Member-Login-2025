package hello.manage.user.service;

import hello.manage.user.domain.Member;
import hello.manage.user.domain.RoleType;
import hello.manage.user.web.dto.MemberAddDto;
import hello.manage.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Member save(MemberAddDto memberAddDto) {

        Member member = Member.builder()
                .memberName(memberAddDto.getMemberName())
                .email(memberAddDto.getEmail())
                .password(passwordEncoder.encode(memberAddDto.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(RoleType.ADMIN)
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    @Override
    public List<Member> findAll() {
        List<Member> allMembers = memberRepository.findAll();
        return allMembers;
    }
}
