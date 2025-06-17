package hello.manage.user.service;

import hello.manage.user.domain.Member;
import hello.manage.user.domain.RoleType;
import hello.manage.user.web.dto.MemberAddDto;
import hello.manage.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Member save(MemberAddDto memberAddDto) {

        Member member = Member.builder()
                .name(memberAddDto.getName())
                .email(memberAddDto.getEmail())
                .password(passwordEncoder.encode(memberAddDto.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(RoleType.ADMIN)
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

}
