package edu.lk.ijse.gdse.aad.aadBackendFinal.exception;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new ApiResponse(HttpStatus.NOT_FOUND.value(),
                        e.getMessage(), null), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {
        return new ResponseEntity<>(
                new ApiResponse(HttpStatus.CONFLICT.value(),
                        e.getMessage(), null),
                HttpStatus.CONFLICT
        );
    }


    @ExceptionHandler(EmptyResourceException.class)
    public ResponseEntity<ApiResponse> handleResourceEmptyException(EmptyResourceException e) {
        return new ResponseEntity<>(
                new ApiResponse(HttpStatus.NO_CONTENT.value(),
                        e.getMessage(), null),
                HttpStatus.NO_CONTENT
        );
    }


    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleRuntimeException
            (RuntimeException ex) {
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                "Internal Server Error"
                );
    }








}
