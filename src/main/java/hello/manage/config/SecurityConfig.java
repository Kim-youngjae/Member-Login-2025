package hello.manage.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티를 활성화하고 웹 보안 설정을 구성 -> 자동으로 시큐리티 필터 체인을 생성하고 웹 보안을 활성화
@EnableMethodSecurity // 메서드 단위로 권한을 체크할 때 config에서 인식할 수 있도록 @PreAuthorize 같은 어노테이션 인식
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /*.csrf((csrf) ->
                        csrf.disable() // 완전한 jwt 토큰 기반 rest api 기반이라면 disable() 해도 됨
                        // 아무것도 없다면 기본이 enable()
                )*/
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests((authorizeRequests) ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                                        // 정적 리소스에 대한 접근 허용 (공통 위치 모두 포함)
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/login", "/error", "/members/add", "/api/**").permitAll()
                                        .requestMatchers("/members/admin/**").hasRole("ADMIN")
                                        .requestMatchers("/members/user/**").hasAnyRole("USER", "ADMIN")
                                        .anyRequest().authenticated()
                        //.requestMatchers("/main/**").hasRole(Role.USER.name()) -> 이렇게 나중에는 권한에 따라 부여할 경로도 추가할 수 있다.
                )
                .formLogin((formLoginConfig) ->
                        formLoginConfig
                                .loginPage("/login")
                                .permitAll()
                );

        return http.build();
    }

    /**
     * 회원가입, 로그인 시 넘어온 비밀번호를 단방향 암호화를 하기 위한 빈 등록
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 커스텀 로그인 처리 시 필요한 인증 매니저를 명시적으로 등록하는 코드
     *
     * @param http
     * @param passwordEncoder
     * @param userDetailsService
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return builder.build();
    }


}
