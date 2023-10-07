package com.okadali.labreportsystemv2backend.utils;

public  class TokenUtils {

    public static String getToken(String tokenWithBearer) {
        return tokenWithBearer.substring(7);
    }
}
