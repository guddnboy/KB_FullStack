package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private String username;        // 사용자 식별자 (수정 불가)
    private String password;        // 현재 비밀번호 (인증용)
    private String email;           // 수정할 이메일
    MultipartFile avatar;           // 새로운 아바타 이미지

    // MemberVO 변환 메서드
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)     // 사용자명은 그대로 유지
                .email(email)           // 이메일만 업데이트
                .build();
    }
}