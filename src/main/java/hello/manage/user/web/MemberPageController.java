package hello.manage.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberPageController {

    @GetMapping("/add")
    public String goMemberForm() {
        return "member/memberForm";
    }
}
