package com.bovespaApi.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.UnknownHostException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnknownHostException.class)
    private ResponseEntity<RestErrorMessage> siteNaoEncontradoHandler(Exception ex) {
        RestErrorMessage threatresponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Site não encontrado, verifique se escreveu o endereço corretamente");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatresponse);
    }

}
