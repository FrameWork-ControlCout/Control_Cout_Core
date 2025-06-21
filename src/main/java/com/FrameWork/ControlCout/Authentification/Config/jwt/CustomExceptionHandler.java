/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.Config.jwt;

import com.FrameWork.ControlCout.web.errors.IllegalBusinessLogiqueException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Administrator
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Unauthorized: You do not have permission to access this resource.");
        return ResponseEntity.badRequest().body(errorResponse);
    }

//     @ExceptionHandler(IllegalBusinessLogiqueException.class)
//    protected ResponseEntity<Object> handleIllegalBusinessLogiqueException(IllegalBusinessLogiqueException ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("description", ex.getMessage()); // Use the message from your exception
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); // Or appropriate status
//    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("description", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
//    // Add handlers for other exceptions as needed.  For example:
//    @ExceptionHandler(Exception.class) // Generic exception handler (use cautiously)
//    protected ResponseEntity<Object> handleGenericException(Exception ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("description", "An unexpected error occurred."); // Generic message
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR); // Only use 500 for truly unexpected errors.  Log the exception details for debugging.
//    }
    @ExceptionHandler(IllegalBusinessLogiqueException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(IllegalBusinessLogiqueException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Cause", ex.getCause());
        body.put("timestamp", LocalDateTime.now());
        body.put("description", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)  // Catch other exceptions
//    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("Field", ex.getClass().getFields());
//        body.put("timestamp", LocalDateTime.now());
//        body.put("description", "An unexpected error occurred."); // General message
//        log.error("Exception caught: ", ex);  // Log the exception for debugging
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Field", ex.getClass().getFields());
        body.put("timestamp", LocalDateTime.now());
        body.put("description", ex.getMessage()); //Or "Resource not found."
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(NoResourceFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("status", ex.getStatusCode());
//        body.put("timestamp", LocalDateTime.now());
//        body.put("description", ex.getMessage()); //Or "Resource not found."
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }

}
