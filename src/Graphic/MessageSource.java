package Graphic;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSource extends ResourceBundleMessageSource {
	private ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	private Locale locale;
		
	public void init(){
		messageSource.setBasename("Messages");
		locale = new Locale("en", "EN");
	}
	
	public String getMessage(String key){
		return messageSource.getMessage(key, null, locale);
	}
	
	public String getMessage(String key, Object[] parameters){
		return messageSource.getMessage(key, parameters, locale);
	}
	
	public ResourceBundleMessageSource getMessageSource(){
		return messageSource;
	}
	
}






