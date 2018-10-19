package com.aspsols.cotizaciones.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.aspsolutions.jforms.commons.StringEncrypter;
import com.aspsolutions.jforms.commons.StringEncrypter.EncryptionException;

public class JSP7PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence password) {		
		String encryptedPassword = "";
		try {
			StringEncrypter encrypter = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DEFAULT_ENCRYPTION_KEY);
			encryptedPassword = encrypter.encrypt((String) password);
		} catch (EncryptionException e) {			
			e.printStackTrace();
		}
		return encryptedPassword;		
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}

}
