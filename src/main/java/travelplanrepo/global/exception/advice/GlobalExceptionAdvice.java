package travelplanrepo.global.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import travelplanrepo.global.exception.dto.ErrorResponse;

@Component
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity allExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(400, e.getClass().getSimpleName(), e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity bindExceptionHandler(BindException be) {
        ErrorResponse errorResponse = new ErrorResponse(400, be.getClass().getSimpleName(),"잘못된 입력값입니다.");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
