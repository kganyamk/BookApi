package com.maleka.kganya.bookapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code GlobalExceptionHandler} is a centralized exception handler that catches exceptions
 * thrown across the entire application and returns customized error responses.
 * <p>
 * It provides handlers for specific exceptions such as validation errors, resource not found,
 * and generic server errors to improve user feedback and debugging.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link MethodArgumentNotValidException}, which occurs when the request body
     * does not conform to the validation constraints defined in the model.
     * <p>
     * It collects all validation errors from the exception and returns them as a
     * map of field names and error messages.
     *
     * @param ex the exception thrown when validation fails
     * @return a {@link ResponseEntity} containing a map of validation errors and a BAD_REQUEST status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Collect all validation errors and map them to their respective fields
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link ResourceNotFoundException}, which is thrown when a requested resource
     * is not found in the application.
     *
     * @param ex the exception indicating the resource was not found
     * @return a {@link ResponseEntity} with an error message and a NOT_FOUND status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        // Return a structured error response for resource not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error", "Resource Not Found",
                "message", ex.getMessage()
        ));
    }

    /**
     * Handles {@link InvalidDataException}, which is thrown when invalid data is encountered.
     *
     * @param ex the exception indicating invalid data
     * @return a {@link ResponseEntity} with an error message and a BAD_REQUEST status
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleInvalidData(InvalidDataException ex) {
        // Return a structured error response for invalid data
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "Invalid Data",
                "message", ex.getMessage()
        ));
    }

    /**
     * Handles general {@link Exception} that is not explicitly caught by other handlers.
     * <p>
     * This ensures that unexpected errors are logged and a generic error response
     * is returned to the client.
     *
     * @param ex the exception indicating an internal server error
     * @return a {@link ResponseEntity} with an error message and an INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        // Return a generic error response for unexpected exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "Internal Server Error",
                "message", "An unexpected error occurred."
        ));
    }
}
