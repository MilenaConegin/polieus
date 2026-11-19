
package br.com.fiap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Dados inválidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> tratarDadosInvalidos(
            IllegalArgumentException e) {

        ErroResponse erro = new ErroResponse(
                400,
                "Dados inválidos",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // 400 - Erros das validações dos TOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(
            MethodArgumentNotValidException e) {

        String mensagem = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Verifique os dados enviados!");

        ErroResponse erro = new ErroResponse(
                400,
                "Erro de validação",
                mensagem
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // 400 - JSON inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> tratarJsonInvalido(
            HttpMessageNotReadableException e) {

        ErroResponse erro = new ErroResponse(
                400,
                "JSON inválido",
                "Verifique o formato dos dados enviados."
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // 404 - Registro não encontrado
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarNaoEncontrado(
            RecursoNaoEncontradoException e) {

        ErroResponse erro = new ErroResponse(
                404,
                "Não encontrado",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 405 - Método HTTP não permitido
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponse> tratarMetodoNaoPermitido(
            HttpRequestMethodNotSupportedException e) {

        ErroResponse erro = new ErroResponse(
                405,
                "Método não permitido",
                "O método HTTP utilizado não é permitido para esta rota."
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
    }

    // 409 - Regra de negócio violada
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponse> tratarRegraNegocio(
            RegraNegocioException e) {

        ErroResponse erro = new ErroResponse(
                409,
                "Conflito",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // 409 - Conflito de estado
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroResponse> tratarConflito(
            IllegalStateException e) {

        ErroResponse erro = new ErroResponse(
                409,
                "Conflito",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // 500 - Erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroInterno(Exception e) {

        ErroResponse erro = new ErroResponse(
                500,
                "Erro interno",
                "Ocorreu um erro interno no servidor."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
