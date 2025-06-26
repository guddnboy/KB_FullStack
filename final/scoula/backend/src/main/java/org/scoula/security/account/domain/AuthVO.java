package org.scoula.security.account.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthVO implements GrantedAuthority {
    private String username;        // 사용자 ID
    private String auth;           // 권한 문자열

    @Override
    public String getAuthority() {
        return auth;               // Spring Security가 권한 확인 시 호출
    }
}