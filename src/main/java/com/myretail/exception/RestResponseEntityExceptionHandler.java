package com.myretail.exception;


import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
	public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	 
	    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class,JSONException.class,ProductNotFoundException.class })
	    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
	    	
	    	 String bodyOfResponse="error in input";
	    	 HttpStatus status= HttpStatus.BAD_REQUEST;
	    	 
	    	if (ex instanceof  IllegalArgumentException ){
	        bodyOfResponse = ex.getMessage();
	        status=HttpStatus.BAD_REQUEST;
	    	}
	    	
	    	else if (ex instanceof  ProductNotFoundException ){
		        bodyOfResponse = ex.getMessage();
		        status=HttpStatus.NOT_FOUND;
		    	}
	    	else if(ex instanceof JSONException)
	    	{
	    		 bodyOfResponse = "error parsing target json response object";
			     status=HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
			else{				
				bodyOfResponse = "Internal Server Error";
		        status=HttpStatus.INTERNAL_SERVER_ERROR;
			}

			return handleExceptionInternal(ex, bodyOfResponse, 
			          new HttpHeaders(), status, request);
	    }
	    
}
	    
	


