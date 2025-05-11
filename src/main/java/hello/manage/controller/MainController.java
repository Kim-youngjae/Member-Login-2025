package hello.manage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    /**
     * 홈 페이지
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    /**
     * 메인 페이지 -> 로그인 후 들어가는 화면
     *
     * @return
     */
    @GetMapping("/main")
    public String homePage(Authentication authentication, Model model) {
        String email = authentication.getName(); // 또는 CustomUserDetails → authentication.getPrincipal()
        model.addAttribute("email", email);
        return "home/main";
    }

    /**
     * 로그인 페이지
     *
     * @return
     */
    @GetMapping("/login")
    public String loginPage() {
        return "home/login";
    }

    //    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/user/test1")
    public String test1(Authentication authentication, Model model) {
        String email = authentication.getName();
        log.debug("isAuthenticated(): {}", authentication.isAuthenticated());
        log.debug("getAuthorities(): {}", authentication.getAuthorities());

        model.addAttribute("email", email);
        return "test1";
    }

    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/test2")
    public String test2(Authentication authentication, Model model) {
        String email = authentication.getName();
        log.debug("isAuthenticated(): {}", authentication.isAuthenticated());
        log.debug("getAuthorities(): {}", authentication.getAuthorities());

        model.addAttribute("email", email);
        return "test2";
    }
}
