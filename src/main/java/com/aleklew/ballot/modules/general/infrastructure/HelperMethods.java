package com.aleklew.ballot.modules.general.infrastructure;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

public class HelperMethods {
    public static String passwordToHash(String password, String userName)
    {
        try
        {
            password = String.format("%s%s", userName, password);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            return new String(Base64.encodeBase64(messageDigest.digest()));
        }
        catch (Exception ex)
        {
            return new String("");
        }
    }
}
