package hello.manage.service;

import hello.manage.domain.Member;
import hello.manage.domain.RoleType;
import hello.manage.dto.member.MemberAddDto;
import hello.manage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Member save(MemberAddDto memberAddDto) {

        Member member = Member.builder()
                .username(memberAddDto.getUsername())
                .email(memberAddDto.getEmail())
                .password(passwordEncoder.encode(memberAddDto.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(RoleType.USER)
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

}
