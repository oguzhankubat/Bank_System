package Finance.Bank_System.core;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return getMessage(key, null);
    }

    public String getMessage(String key, Object[] args) {
        Locale locale = Locale.getDefault(); 
        return messageSource.getMessage(key, args, locale);
    }
}