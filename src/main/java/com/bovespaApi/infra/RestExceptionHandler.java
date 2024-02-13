package com.bovespaApi.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.UnknownHostException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(UnknownHostException.class)
    private ResponseEntity<RestErrorMessage> siteNaoEncontradoHandler(UnknownHostException ex) {
        log.info("Exceção capturada: {}", ex.getMessage());
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Site não encontrado, verifique se escreveu o endereço corretamente");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatresponse);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    private ResponseEntity<RestErrorMessage> NumeroForaDoRagelistHandler(IndexOutOfBoundsException ex) {
        log.info("Exceção capturada: {}", ex.getMessage());
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatresponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<RestErrorMessage> ListaVaziaHandler(NoSuchElementException ex) {
        log.info("Exceção capturada: {}", ex.getMessage());
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatresponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<RestErrorMessage> ErrorHandler(IllegalArgumentException ex) {
        log.info("Exceção capturada: {}", ex.getMessage());
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatresponse);
    }
}