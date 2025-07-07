package hello.manage.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("회원 정보를 찾을 수 없습니다.");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
