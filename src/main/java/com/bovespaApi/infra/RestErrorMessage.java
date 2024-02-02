package com.bovespaApi.infra;

import org.springframework.http.HttpStatus;

public class RestErrorMessage {
    private HttpStatus status;
    private String message;

    // Construtor padrão
    public RestErrorMessage() {
    }

    // Construtor com parâmetros
    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter para status
    public HttpStatus getStatus() {
        return status;
    }

    // Setter para status
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    // Getter para message
    public String getMessage() {
        return message;
    }

    // Setter para message
    public void setMessage(String message) {
        this.message = message;
    }
}

