package com.wxmetro.ic.test;

import com.wxmetro.ic.common.security.Digests;
import com.wxmetro.ic.common.utils.Encodes;

public class PasswordTest {

	private static final int SALT_SIZE = 8;
	private static final int HASH_INTERATIONS = 1024;

	public static void main(String[] args) {
		String pas
	}

	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}

}
