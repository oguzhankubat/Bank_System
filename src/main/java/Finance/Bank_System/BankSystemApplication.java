package Finance.Bank_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

}
