package med.voll.api.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class APIExceptionHandler{

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

}
