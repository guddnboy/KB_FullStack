package org.scoula.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;
    private List<String> roles;


    /**
     * MemberVO에서 UserInfoDTO로 변환하는 팩토리 메서드
     * @param member
     * @return 변환된 UserInfoDTO
     */
    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO(
                member.getUsername(),
                member.getEmail(),
                member.getAuthList().stream()
                        .map(a -> a.getAuth())  // 권한 리스트를 문자열로 변환
                        .toList()
        );
    }
}
