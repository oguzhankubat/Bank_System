package Finance.Bank_System.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class config {
	@Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
	
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages_en");
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setFallbackToSystemLocale(false);
	    return messageSource;
	}
	
}
