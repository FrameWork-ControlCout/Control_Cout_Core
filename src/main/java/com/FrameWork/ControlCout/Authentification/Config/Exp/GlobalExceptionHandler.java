///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.FrameWork.ControlCout.Authentification.Config.Exp;
        
 
//
///**
// *
// * @author Administrator
// */import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        String errorMessage = String.format("Required parameter '%s' is missing.", parameterName);
        return sendErrorResponse(HttpStatus.BAD_REQUEST, errorMessage); // Use helper method
    }
    
   

 
    
//   @ExceptionHandler(Exception.class)  // Handles all generic exceptions
//    public ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
//        CustomErrorResponse error = new CustomErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // 500
//    }
//
//
//    @ExceptionHandler(IllegalArgumentException.class) // Example of handling a specific exception
//    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
//        CustomErrorResponse error = new CustomErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);  // 400
//    }
//
//    // Add more exception handlers for specific exception types as needed (e.g., NotFoundException, etc.)
//
//
//    // Inner class for custom error response structure
//    public static class CustomErrorResponse {
//        private Date timestamp;
//        private String description;
//        private String details;
//
//        public CustomErrorResponse(Date timestamp, String description, String details) {
//            super();
//            this.timestamp = timestamp;
//            this.description = description;
//            this.details = details;
//        }
//
//        // Getters for timestamp, message, and details
//
//        public Date getTimestamp() {
//            return timestamp;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//         
//
//        public String getDetails() {
//            return details;
//        }
//        
//    }
    

    private ResponseEntity<Map<String, Object>> sendErrorResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", status.value());
        errorResponse.put("description", message);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }
    
   
}
