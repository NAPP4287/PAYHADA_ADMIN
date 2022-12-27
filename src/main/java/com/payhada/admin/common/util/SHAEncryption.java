package com.payhada.admin.common.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class SHAEncryption {
	public static String encrypt512(String password) {
		String encPw = password;

		if (password != null && !password.equals("")) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				md.update(password.getBytes());
				encPw = String.format("%0128x", new BigInteger(1, md.digest()));
			} catch (NullPointerException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return encPw;
	}
}
