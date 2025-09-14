package com.example.jpa.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleNotFound_ShouldReturnNotFoundResponse() {
    
        RuntimeException exception = new RuntimeException("Course not found");

      
        ResponseEntity<String> response = globalExceptionHandler.handleNotFound(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Error: Course not found");
    }

    @Test
    void handleGeneral_ShouldReturnInternalServerErrorResponse() {
        Exception exception = new Exception("Database connection failed");

        ResponseEntity<String> response = globalExceptionHandler.handleGeneral(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Something went wrong!");
    }
}
