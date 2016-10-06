package com.tf.usermanagement.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.usermanagement.errorhandler.Message;
import com.tf.usermanagement.exception.CatalogException;
import com.tf.usermanagement.exception.GenericQueryException;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.exceptions.InsufficientDataException;

/**
 * Handles all exception of Element Exploration.
 * 
 * @author Santosh, Manideep
 *
 */
@ControllerAdvice
public class RestErrorHandler {    
    
    @ExceptionHandler(value = { InsufficientDataException.class })
    @ResponseBody
    public String insufficientDataException(
	    InsufficientDataException exception) {
	return exception.getMessage();
    }
   
    /**
     * used to throw if there is any empty list
     * @param exception
     * @return
     * @author Manideep
     */
    @ExceptionHandler(value = { EmptyListException.class })
    @ResponseBody
    public Message emptyListException(
    		EmptyListException exception) {
	return exception.getErrorMessage();
    }
    
    @ExceptionHandler(value = { CatalogException.class })
    @ResponseBody
    public Message CatalogException(
    		CatalogException exception) {
	return exception.getErrorMessage();
    }
    
    @ExceptionHandler(value = { GenericQueryException.class })
    @ResponseBody
    public ResponseEntity<?> GenericQueryException(GenericQueryException exception) {
    	return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
