package br.gbank.gbank.resources;

import br.gbank.gbank.error.ApiErrorResponse;
import br.gbank.gbank.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(GenericException genericException) {
        return new ResponseEntity<>(new ApiErrorResponse("error-400/generic", genericException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintDeclarationException(ConstraintException constraintDeclarationException) {
        return new ResponseEntity<>(new ApiErrorResponse("erro-400/constraint", constraintDeclarationException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ApiErrorResponse("erro-404/notFound", notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotValidException(MethodNotValidException methodNotValidException) {
        return new ResponseEntity<>(new ApiErrorResponse("erro-400/method", methodNotValidException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContaSemSaldoException.class)
    public ResponseEntity<ApiErrorResponse> handleContaSemSaldoException(ContaSemSaldoException contaSemSaldoException) {
        return new ResponseEntity<>(new ApiErrorResponse("erro-400/method", contaSemSaldoException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MoedaNaoSuportadaException.class)
    public ResponseEntity<ApiErrorResponse> handleMoedaNaoSuportadaException(MoedaNaoSuportadaException moedaNaoSuportadaException) {
        return new ResponseEntity<>(new ApiErrorResponse("erro-400/currency", moedaNaoSuportadaException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
