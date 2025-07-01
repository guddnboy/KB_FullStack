import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

import axios from 'axios'; // axios 임포트 // <- 추가

// 초기 상태 템플릿
const initState = {
  token: '', // JWT 접근 토큰
  user: {
    username: '', // 사용자 ID
    email: '', // 이메일
    roles: [], // 권한 목록
  },
};

// 스토어 정의
export const useAuthStore = defineStore('auth', () => {
  const state = ref({ ...initState });

  // Computed 속성들
  const isLogin = computed(() => !!state.value.user.username); // 로그인 여부
  const username = computed(() => state.value.user.username); // 사용자명
  const email = computed(() => state.value.user.email); // 이메일

  // isLogin 사용자명 존재 여부로 로그인 상태 판단
  // username, email 반응형 데이터로 컴포넌트에서 자동 업데이트
  // !! 연산자로 boolean 타입 변환 보장

  // 액션 메서드 작성 영역

  // 로그인 액션
  const login = async (member) => {
    // 임시 테스트용 로그인 (실제 API 호출 전) <- 주석 처리
    // state.value.token = 'test token';
    // state.value.user = {
    //   username: member.username,
    //   email: member.username + '@test.com',
    // };

    // 실제 API 호출 <- 추가
    const { data } = await axios.post('/api/auth/login', member);
    state.value = { ...data }; // 서버 응답 데이터로 상태 업데이트

    // localStorage에 상태 저장
    localStorage.setItem('auth', JSON.stringify(state.value));
  };

  // 로그아웃 액션
  const logout = () => {
    localStorage.clear(); // localStorage 완전 삭제
    state.value = { ...initState }; // 상태를 초기값으로 리셋
  };

  // 상태 복원 로직
  // - localStorage에 인증 정보(auth)가 저장되어 있을 경우 상태 복원
  const load = () => {
    const auth = localStorage.getItem('auth');
    if (auth != null) {
      state.value = JSON.parse(auth); // JSON 문자열을 객체로 변환
      console.log(state.value);
    }
  };

  // 스토어 초기화 시 자동 실행
  load();

  // 외부에서 사용할 수 있도록 반환
  return {
    state,
    username,
    email,
    isLogin,
    login,
    logout,
    getToken: () => state.value.token,
  };
});
