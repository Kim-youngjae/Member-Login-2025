package hello.manage.config;

import hello.manage.user.domain.Member;
import hello.manage.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 사용자가 로그인 요청 (/login, POST)
     * UsernamePasswordAuthenticationFilter가 로그인 시도를 감지
     *
     * 내부적으로 UserDetailsService.loadUserByUsername() 실행 ← 여기서 내가 만든 CustomUserDetailService 사용
     *
     * UserDetails를 반환해야 함 ← 여기서 내가 만든 CustomUserDetails가 반환됨
     *
     * 비밀번호 비교, 권한 체크 등 Security 내부 로직 실행
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다." + email));

        return new CustomUserDetails(member);
    }
}
