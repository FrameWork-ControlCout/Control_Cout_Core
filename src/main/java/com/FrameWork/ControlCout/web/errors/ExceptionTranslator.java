package com.FrameWork.ControlCout.web.errors;

//import com.FrameWork.ControlCout.Authentification.web.Response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Controller advice to translate the server side exceptions to client-friendly
 * json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    @Autowired
    MessageSource messages;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorVM dto = new ErrorVM("VALIDATION ERROR");
        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
        }
        return dto;
    }

    @ExceptionHandler(MyResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorVM> processRessourceNotFound(MyResourceNotFoundException exception) {
        BodyBuilder builder;
        builder = ResponseEntity.status(HttpStatus.NOT_FOUND);
        Locale loc = LocaleContextHolder.getLocale();
        String translatedmessage = messages.getMessage(exception.getMessage(), null, loc);
        return builder.body(new ErrorVM("NOT FOUND", translatedmessage));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorVM> processMethodNotSupportedException(IllegalArgumentException exception) {
            log.error("IllegalArgumentException", exception);
        BodyBuilder builder;
        builder = ResponseEntity.status(HttpStatus.CONFLICT);
        Object[] obj = new Object[1];
        if (exception.getCause() != null) {
            obj = exception.getCause().getMessage().split(";");

        }
        Locale loc = LocaleContextHolder.getLocale();
        String translatedmessage = messages.getMessage(exception.getMessage(), obj, loc);
        return builder.body(new ErrorVM("CONFLICT", translatedmessage));
    }
 

    @ExceptionHandler(IllegalBusinessLogiqueException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorVM> processMethodNotSupportedException(IllegalBusinessLogiqueException exception) {
        log.error("IllegalBusinessLogiqueException", exception);
        Locale loc = LocaleContextHolder.getLocale();
        Object[] obj = new Object[1];
        if (exception.getCause() != null) {
            obj = exception.getCause().getMessage().split(";");

        }
        String translatedmessage = messages.getMessage(exception.getMessage(), obj, loc);
        BodyBuilder builder;
        builder = ResponseEntity.status(HttpStatus.CONFLICT);
        return builder.body(new ErrorVM("CONFLICT", translatedmessage));
    }
}
