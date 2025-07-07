package hello.manage.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 사용자 관련 전역 예외 처리기
 */
@ControllerAdvice
public class MemberGlobalExceptionHandler {

    /**
     * 회원정보가 없을 떄 예외처리
     */
    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(MemberNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/500";
    }
}
