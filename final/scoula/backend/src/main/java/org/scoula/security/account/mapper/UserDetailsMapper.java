package org.scoula.security.account.mapper;

import org.scoula.security.account.domain.MemberVO;

public interface UserDetailsMapper {
    /**
     * 사용자명으로 사용자 정보와 권한 정보를 조회
     * @param username 사용자 ID
     * @return 사용자 정보 및 권한 목록
     */
    public MemberVO get(String username);
}