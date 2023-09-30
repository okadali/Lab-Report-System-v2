package com.okadali.labreportsystemv2backend.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData<T> {
    boolean success;
    String message;
    T data;
}
