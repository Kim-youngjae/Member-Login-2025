package hello.manage.controller;

import hello.manage.dto.member.LoginDto;
import hello.manage.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * post 폼 로그인 방식
     */
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session) {
        try {
            loginService.login(loginDto, session);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

}
