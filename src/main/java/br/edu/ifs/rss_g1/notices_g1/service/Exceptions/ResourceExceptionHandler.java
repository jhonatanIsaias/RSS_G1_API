package br.edu.ifs.rss_g1.notices_g1.service.Exceptions;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> errorValidate(MethodArgumentNotValidException e, HttpServletRequest http){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"Validation Error",http.getRequestURI());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            standardError.getListErrors().add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(standardError.getStatus()).body(standardError);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest http){

        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"Not Found",http.getRequestURI());
        standardError.getListErrors().add(e.getMessage());
        return ResponseEntity.status(standardError.getStatus()).body(standardError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraint(ConstraintViolationException e, HttpServletRequest http){

        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"Not Found",http.getRequestURI());
        standardError.getListErrors().add(e.getMessage());
        return ResponseEntity.status(standardError.getStatus()).body(standardError);
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<StandardError> userRegister(UserException e, HttpServletRequest http){

        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"Unable to register user",http.getRequestURI());
        standardError.getListErrors().add(e.getMessage());
        return ResponseEntity.status(standardError.getStatus()).body(standardError);
    }



}
