package com.bovespaApi.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.UnknownHostException;
import java.util.NoSuchElementException;

@ControllerAdvice
// O Objetivo dessa Class é tratar as exceções que podem ocorrer na aplicação de forma mais elegante e amigável para o usuário
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Método para tratar exceção de site não encontrado
    @ExceptionHandler(UnknownHostException.class)
    private ResponseEntity<RestErrorMessage> siteNaoEncontradoHandler() {
        // Cria um objeto RestErrorMessage com a mensagem de erro
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Site não encontrado, verifique se escreveu o endereço corretamente");
        // Retorna uma mensagem de erro amigável para o usuário
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatresponse);
    }

    // Método para tratar exceção de recurso não encontrado
    @ExceptionHandler(IndexOutOfBoundsException.class)
    private ResponseEntity<RestErrorMessage> NumeroForaDoRagelistHandler(Exception ex) {
        // Cria um objeto RestErrorMessage com a mensagem de erro
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        // Retorna uma mensagem de erro amigável para o usuário
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatresponse);
    }

    // Método para tratar exceção de recurso não encontrado
    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<RestErrorMessage> ListaVaziaHandler(Exception ex) {
        // Cria um objeto RestErrorMessage com a mensagem de erro
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        // Retorna uma mensagem de erro amigável para o usuário
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatresponse);
    }

    // Método para tratar exceção de recurso não encontrado
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<RestErrorMessage> ErrorHandler(Exception ex) {
        // Cria um objeto RestErrorMessage com a mensagem de erro
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        // Retorna uma mensagem de erro amigável para o usuário
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatresponse);
    }

}
