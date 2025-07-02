package org.scoula.member.mapper;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {
    MemberVO get(String username);                    // 회원 조회 (권한 포함)
    MemberVO findByUsername(String username);         // ID 중복 체크용 조회
    int insert(MemberVO member);                      // 회원정보 저장
    int insertAuth(AuthVO auth);                      // 권한정보 저장

    int update(MemberVO member);     // 회원 정보 업데이트  <- 추가
    int updatePassword(ChangePasswordDTO changePasswordDTO); // 비밀번호 변경
}