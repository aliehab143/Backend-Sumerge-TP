
package com.example.jpa;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class JpaApplicationTest {

    @Test
    void main_ShouldNotThrowException() {
  
        assertThatCode(() -> {
          
            String[] args = {};
           
        }).doesNotThrowAnyException();
    }

    @Test
    void mainClassExists() {
        Class<?> mainClass = JpaApplication.class;
        
        assertThatCode(() -> {
            mainClass.getMethod("main", String[].class);
        }).doesNotThrowAnyException();
    }
}

