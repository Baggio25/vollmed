package med.voll.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.exception.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@RestControllerAdvice
public class APIExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidacaoException.class)
    protected ResponseEntity tratarErroRegraNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }
    private record DadosErroValidacao(String campo, String msg) {
        public DadosErroValidacao(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
