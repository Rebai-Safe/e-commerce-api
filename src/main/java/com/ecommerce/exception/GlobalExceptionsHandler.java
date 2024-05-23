package com.ecommerce.exception;

import com.ecommerce.model.ApiResponse;
import com.ecommerce.util.ApiResponseHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger( GlobalExceptionsHandler.class);
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        LOGGER.info("Handling bad credentials exception...");
        return  ApiResponseHandlerUtil.generateResponse("Bad credentials",
                HttpStatus.BAD_REQUEST,
                badCredentialsException.getMessage());
    }
}
