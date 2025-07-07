package hello.manage;

import hello.manage.config.CustomUserDetails;
import hello.manage.user.domain.Member;
import hello.manage.user.web.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class MainController {

    /**
     * 홈 페이지
     *
     * @return
     */
    @GetMapping("/")
    public String index(Authentication authentication, Model model) {

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return "home/index"; // 비로그인 사용자는 index
        }

        // 로그인 된 사용자
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = userDetails.getMember();
        model.addAttribute("member", member);
        model.addAttribute("memberId", member.getId());

        if (authentication.isAuthenticated()) {
            return "home/main";
        }

        return "home/index";
    }

    /**
     * 메인 페이지 -> 로그인 후 들어가는 화면
     *
     * @return
     */
    @GetMapping("/main")
    public String homePage(Authentication authentication, Model model) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = userDetails.getMember();

        model.addAttribute("member", member);
        model.addAttribute("memberId", member.getId());

        //String email = authentication.getName(); // 또는 CustomUserDetails → authentication.getPrincipal()
        //model.addAttribute("email", email);

        return "home/main";
    }

    /**
     * 로그인 페이지
     *
     * @return
     */
    @GetMapping("/login")
    public String loginPage(@ModelAttribute LoginDto loginDto) {
        return "home/login";
    }
}
