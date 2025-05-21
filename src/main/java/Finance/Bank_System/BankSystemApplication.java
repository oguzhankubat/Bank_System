package Finance.Bank_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Finance.Bank_System.Exceptions.ProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class BankSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}
	
	@ExceptionHandler
	public ProblemDetails handleExceptionRuntime(RuntimeException runtimeException) {
		ProblemDetails problemDetails =new ProblemDetails();
		problemDetails.setMessage(runtimeException.getMessage());
		return problemDetails;
	}
	
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request format. Please check the data you sent.");
    }
	
	@ExceptionHandler
	public ProblemDetails handleExceptionMethod(MethodArgumentNotValidException methodArgumentNotValidException) {
	    ProblemDetails problemDetails = new ProblemDetails();

	    StringBuilder errorMessageBuilder = new StringBuilder();
	    

	    methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> {
	        errorMessageBuilder.append(fieldError.getField())
	                .append(": ")
	                .append(fieldError.getDefaultMessage())
	                .append("; ");
	    });


	    if (errorMessageBuilder.length() == 0) {
	        errorMessageBuilder.append("Geçersiz giriş!");
	    }

	    problemDetails.setMessage(errorMessageBuilder.toString().trim());
	    
	    return problemDetails;
	}

}
