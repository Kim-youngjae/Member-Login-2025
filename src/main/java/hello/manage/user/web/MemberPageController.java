package hello.manage.user.web;

import hello.manage.config.CustomUserDetails;
import hello.manage.exception.MemberNotFoundException;
import hello.manage.user.domain.Member;
import hello.manage.user.repository.MemberRepository;
import hello.manage.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberPageController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String goMemberForm() {
        return "member/memberForm";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/dashboard")
    public String goAdminDashboardPage(Authentication authentication, Model model) {

        // 혹시 사용자가 url 상으로 관리자 페이지로 접근하려고 했을 떄 권한이 없다는 오류페이지를 렌더링
        authentication.getAuthorities();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = userDetails.getMember();
        model.addAttribute("memberId", member.getId());

        log.debug("isAuthenticated(): {}", authentication.isAuthenticated());
        log.debug("getAuthorities(): {}", authentication.getAuthorities());

        List<Member> allMembers = memberService.findAll();
        model.addAttribute("members", allMembers);

        return "member/admin/dashboard";
    }

    /**
     * 내 정보 조회 화면
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{memberId}")
    public String goUserInfoPage(@PathVariable("memberId") Long memberId, Authentication authentication, Model model) {
        // 로그인이 되어 있지 않으면 로그인 화면으로 보내고 싶음
        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:home/login"; // 로그인 페이지로 이동
        }


        // 관리자가 요청한건지 검사를 해야함. 해당 로그인 한 사용자가 관리자 권한을 가지고 있는지. -> 단순 검사라 @PreAuthorize로 대신
        Optional<Member> oMember = memberRepository.findById(memberId);

        if (!oMember.isPresent()) {
            throw new MemberNotFoundException("회원정보가 존재하지 않습니다.");
        }

        // 회원정보 리턴
        Member member = oMember.get();
        model.addAttribute("member", member);

        return "member/my-info";
    }
}
