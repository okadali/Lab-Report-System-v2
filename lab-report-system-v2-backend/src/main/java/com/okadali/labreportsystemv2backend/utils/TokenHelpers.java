package com.okadali.labreportsystemv2backend.utils;

public  class TokenHelpers {

    public static String getToken(String tokenWithBearer) {
        return tokenWithBearer.substring(7);
    }
}
