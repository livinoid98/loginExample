package com.inno.loginExample.security;

import java.util.HashMap;
import java.util.Map;

/**
 * 상세 에러코드 정의
 */
public enum ErrorCodes {
    GENERIC(2000),

    /**
     * 사용자 인증(Authentication) 및 권한 부여 (Authorization) 관련 에러코드
     */
    NEED_LOGIN(2300),
    BAD_CREDENTIALS_ERROR(2301),
    LOGIN_ERROR(2302),
    NEED_ROLE(2303),
    NO_USER_ROLE(2304),
    SESSION_EXPIRE(2305),

    ACCOUNT_STATUS_DISABLED(2310),
    ACCOUNT_STATUS_LOCKED(2311),
    ACCOUNT_STATUS_EXPIRED(2312),
    ACCOUNT_STATUS_PASSWORD_EXPIRED(2313);

    final private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getResult() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", this.code);
        result.put("message", this.name().toLowerCase());

        return result;
    }
}
