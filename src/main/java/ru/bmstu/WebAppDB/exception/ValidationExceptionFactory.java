package ru.bmstu.WebAppDB.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidationExceptionFactory {

    private final MessageSource messageSource;

    public ValidationExceptionFactory(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ValidationException create(String key) {
        String message = messageSource.getMessage("error.validation." + key, null, LocaleContextHolder.getLocale());
        return new ValidationException(message);
    }
}
