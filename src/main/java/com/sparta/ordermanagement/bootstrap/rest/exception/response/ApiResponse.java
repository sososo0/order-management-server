package com.sparta.ordermanagement.bootstrap.rest.exception.response;

import com.sparta.ordermanagement.bootstrap.rest.exception.exceptions.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse {

    public static ResponseEntity<Error> error(String message, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
            .body(Error.of(message));
    }

    public static ResponseEntity<Error> error(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(Error.of(errorCode.getMessage()));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Error {

        private String message;

        public static Error of(String errorMessage) {
            return new Error(errorMessage);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Success<T> {

        private T data;

        public static <T> Success<T> of(T data) {
            return new Success<>(data);
        }
    }
}
