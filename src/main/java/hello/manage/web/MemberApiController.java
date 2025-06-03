package hello.manage.web;

import hello.manage.domain.Member;
import hello.manage.web.dto.member.MemberAddDto;
import hello.manage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/add")
    public ResponseEntity<Void> addMember(@RequestBody MemberAddDto memberAddDto) {
        Member savedMember = memberService.save(memberAddDto);

        if (savedMember == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
