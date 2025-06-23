package org.scoula.security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String username;              // 사용자 ID
    private String password;              // 암호화된 비밀번호
    private String email;                 // 이메일 주소
    private Date regDate;                 // 등록일시
    private Date updateDate;              // 수정일시

    private List<AuthVO> authList;        // 권한 목록 (1:N 관계)
}