package org.scoula.member.service;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;

public interface MemberService {
    boolean checkDuplicate(String username);     // ID 중복 체크
    MemberDTO get(String username);              // 회원 조회
    MemberDTO join(MemberJoinDTO member);        // 회원가입

    MemberDTO update(MemberUpdateDTO member);    // 회원정보 수정 <- 추가
    void changePassword(ChangePasswordDTO changePassword); // 비밀번호 변경
}