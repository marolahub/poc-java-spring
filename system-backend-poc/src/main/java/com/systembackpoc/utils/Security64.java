package com.systembackpoc.utils;

import java.util.Base64;

public class Security64 {

    public static String encoderBase64(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
    public static String decoderBase64(String message){
        byte[] decoded = Base64.getDecoder().decode(message);
        return decoded.toString();
    }
}
