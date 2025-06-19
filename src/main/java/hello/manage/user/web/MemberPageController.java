package hello.manage.user.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberPageController {

    @GetMapping("/add")
    public String goMemberForm() {
        return "member/memberForm";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/dashboard")
    public String goAdminDashboardPage(Authentication authentication, Model model) {
        String email = authentication.getName();
        log.debug("isAuthenticated(): {}", authentication.isAuthenticated());
        log.debug("getAuthorities(): {}", authentication.getAuthorities());

        model.addAttribute("email", email);
        return "member/admin/dashboard";
    }
}
