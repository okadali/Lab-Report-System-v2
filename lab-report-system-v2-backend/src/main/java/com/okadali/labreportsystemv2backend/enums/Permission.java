package com.okadali.labreportsystemv2backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permission;
}
