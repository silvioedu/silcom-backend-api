package com.silcom.manager.api.exception;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceInUseException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
	private MessageSource messageSource;

    @ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleApiNotFoundException(RuntimeException ex) {

        Problem problem = createProblemBuilder(
            ProblemTypeEnum.RESOURCE_NOT_FOUND.getStatus(), 
            ProblemTypeEnum.RESOURCE_NOT_FOUND,
            ex.getMessage(),
            null)
            .build();

		return ResponseEntity
            .status(problem.getStatus())
            .body(problem);
	}          
   
    @ExceptionHandler({ DuplicateKeyException.class })
	public ResponseEntity<Object> handleApiDuplicateKeyException(RuntimeException ex) {

        Problem problem = createProblemBuilder(
            ProblemTypeEnum.DUPLICATE_KEY.getStatus(), 
            ProblemTypeEnum.DUPLICATE_KEY,
            ex.getMessage(),
            null)
            .build();

		return ResponseEntity
            .status(problem.getStatus())
            .body(problem);
	}          

    @ExceptionHandler({ ResourceInUseException.class })
	public ResponseEntity<Object> handleApiInUseException(RuntimeException ex) {

        Problem problem = createProblemBuilder(
            ProblemTypeEnum.RESOURCE_IN_USE.getStatus(), 
            ProblemTypeEnum.RESOURCE_IN_USE,
            ex.getMessage(),
            null)
            .build();

		return ResponseEntity
            .status(problem.getStatus())
            .body(problem);
	}          

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Field> problemFields = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

            String name = objectError.getObjectName();

            if (objectError instanceof FieldError) {
                name = ((FieldError) objectError).getField();
            }

            return Field.builder().name(name).userMessage(message).build();
        }).collect(Collectors.toList());
                
        Problem problem = createProblemBuilder(
            ProblemTypeEnum.INVALID_PARAMETER.getStatus(), 
            ProblemTypeEnum.INVALID_PARAMETER,
            "Parâmetros inválidos",
            problemFields)
            .build();

        return ResponseEntity
            .status(problem.getStatus())
            .body(problem);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemTypeEnum problemType, String detail, List<Field> fields) {
		return Problem.builder()
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
			.detail(detail)
            .fields(fields)
            .timestamp(OffsetDateTime.now());
	}
}
