package br.com.erudio.exception;

        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistException(String exception) {
        super(exception);
    }
}
