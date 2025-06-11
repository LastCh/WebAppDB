package ru.bmstu.WebAppDB.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class NotFoundExceptionFactory {

    private final MessageSource messageSource;

    public NotFoundExceptionFactory(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public NotFoundException create(String entityKey) {
        String message = messageSource.getMessage(
                "error." + entityKey.toLowerCase() + ".notfound",
                null,
                LocaleContextHolder.getLocale()
        );
        return new NotFoundException(message);
    }
}
