package com.tdm.resourcebundle;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MessageUtils{
	
	public static String getMessage(String key) {

        try {
            MessageSource bean = ApplicationContextProvider.getContext().getBean(MessageSource.class);
            return bean.getMessage(key, null, Locale.getDefault());
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }

    }
}
