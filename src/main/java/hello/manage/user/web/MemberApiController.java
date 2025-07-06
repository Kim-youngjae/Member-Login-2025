package hello.manage.user.web;

import hello.manage.user.domain.Member;
import hello.manage.user.web.dto.MemberAddDto;
import hello.manage.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

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
