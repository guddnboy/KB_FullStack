package org.scoula.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;
import org.scoula.member.exception.PasswordMissmatchException;
import org.scoula.member.mapper.MemberMapper;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final PasswordEncoder passwordEncoder;  // 비밀번호 암호화
    final MemberMapper mapper;              // 데이터 접근


    // 아이디 중복 체크
    @Override
    public boolean checkDuplicate(String username) {
        MemberVO member = mapper.findByUsername(username);
        //return member != null ? true : false;  // true: 중복(사용불가), false: 사용가능
        return member!= null;
    }

    // 회원 정보 조회
    @Override
    public MemberDTO get(String username) {
        MemberVO member = Optional.ofNullable(mapper.get(username))
                .orElseThrow(NoSuchElementException::new);
        return MemberDTO.of(member);
    }

    // 아바타 파일 저장
    private void saveAvatar(MultipartFile avatar, String username) {
        if(avatar != null && !avatar.isEmpty()) {
            File dest = new File("/Users/guddn/Desktop/uploads/", username + ".png");
            try {
                avatar.transferTo(dest);  // 파일 저장
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 회원 가입(선언적 트랜잭션 처리)
    @Transactional  // 트랜잭션 처리 보장
    @Override
    public MemberDTO join(MemberJoinDTO dto) {
        MemberVO member = dto.toVO();

        // 1. 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 2. 회원정보 저장
        mapper.insert(member);

        // 3. 권한정보 저장
        AuthVO auth = new AuthVO();
        auth.setUsername(member.getUsername());
        auth.setAuth("ROLE_MEMBER");  // 기본 권한 설정
        mapper.insertAuth(auth);

        // 4. 아바타 파일 저장
        saveAvatar(dto.getAvatar(), member.getUsername());

        // 5. 저장된 회원정보 반환
        return get(member.getUsername());
    }

    // 회원 정보 수정 서비스
    @Override
    public MemberDTO update(MemberUpdateDTO member) {
        // 1. 기존 회원 정보 조회
        MemberVO vo = mapper.get(member.getUsername());

        // 2. 비밀번호 검증 (보안 핵심 로직)
        if(!passwordEncoder.matches(member.getPassword(), vo.getPassword())) {
            throw new PasswordMissmatchException();
        }

        // 3. 회원정보 업데이트
        mapper.update(member.toVO());

        // 4. 아바타 이미지 저장
        saveAvatar(member.getAvatar(), member.getUsername());

        // 5. 업데이트된 정보 반환
        return get(member.getUsername());
    }

    // 비밀번호 변경 서비스
    @Override
    public void changePassword(ChangePasswordDTO changePassword) {
        // 1. 사용자 정보 조회
        MemberVO member = mapper.get(changePassword.getUsername());

        // 2. 기존 비밀번호 검증
        if(!passwordEncoder.matches(changePassword.getOldPassword(), member.getPassword())) {
            throw new PasswordMissmatchException();
        }

        // 3. 새 비밀번호 암호화
        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));

        // 4. 데이터베이스 업데이트
        mapper.updatePassword(changePassword);
    }

}