package com.ecommerce.util;

import com.ecommerce.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseHandlerUtil {

    public static ResponseEntity<ApiResponse> generateResponse(String message, HttpStatus status, Object object){
        ApiResponse response = new ApiResponse(message, status.value(), object);
        return new ResponseEntity<>(response, status);
    }
}
