package gryc.bank.demo.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            AccountNotFoundException.class
    })
    public ResponseEntity<AccountErrorResponse> handleMissingParam(Exception ex, WebRequest request) {
        AccountErrorResponse errors = new AccountErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
