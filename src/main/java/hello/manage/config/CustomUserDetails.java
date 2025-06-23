package hello.manage.config;

import hello.manage.user.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * CustomUserDetails와 CustomUserDetailService는 Spring Security의 인증 과정에서 '로그인 사용자 정보를 어떻게 불러오고 사용할지'를 커스터마이징하기 위해 만든 것
 * CustomUserDetailService:	로그인할 때 이메일로 사용자를 찾고, Spring Security에게 사용자 정보를 넘겨주는 클래스
 * CustomUserDetails: 찾은 사용자 정보를 Spring Security가 이해할 수 있는 형태(UserDetails)로 감싸는 클래스
 *
 * Spring Security가 로그인한 사용자의 인증 정보를 확인할 수 있게 하는 핵심 클래스들
 */
public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() { // principal의 getName() -> 로그인 식별자인 email을 가져오는 것과 동일함
        return member.getEmail();
    }

    public String getRealName() {
        return member.getMemberName();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return true;
    }

    public Member getMember() {
        return member;
    }
}
