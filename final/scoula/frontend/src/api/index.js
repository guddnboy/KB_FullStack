import axios from 'axios';
import { useAuthStore } from '@/stores/auth';
import router from '@/router';

// Axios 인스턴스 생성
const instance = axios.create({
  timeout: 1000, // 요청 타임아웃 설정(1000 == 1초)
});

// 요청 인터셉터 - 모든 요청에 JWT 토큰 자동 추가
instance.interceptors.request.use(
  (config) => {
    // Auth Store에서 토큰 추출
    const { getToken } = useAuthStore();
    const token = getToken();

    if (token) {
      // Authorization 헤더에 Bearer 토큰 추가
      // (Bearer 토큰은 OAuth 2.0과 JWT에서 사용하는 표준 인증 방식)
      config.headers['Authorization'] = `Bearer ${token}`;
      console.log(config.headers.Authorization);
    }

    return config; // 수정된 config 반환
  },
  (error) => {
    // 요청 중 에러 발생 시 처리
    return Promise.reject(error);
  }
);

// 응답 인터셉터 - 에러 응답 자동 처리
instance.interceptors.response.use(
  (response) => {
    // 정상 응답 처리 (200, 404 등)
    if (response.status === 200) {
      return response;
    }

    if (response.status === 404) {
      return Promise.reject('404: 페이지 없음 ' + response.request);
    }

    return response;
  },
  async (error) => {
    // 에러 응답 처리 (401, 403, 500 등)
    if (error.response?.status === 401) {
      const { logout } = useAuthStore();
      logout(); // 자동 로그아웃
      router.push('/auth/login?error=login_required'); // 로그인 페이지로 이동

      // 401 Unauthorized 에러 발생 시 자동으로 로그아웃하고 로그인 페이지로 이동
      return Promise.reject({
        error: '로그인이 필요한 서비스입니다.',
      });
    }

    return Promise.reject(error);
  }
);

export default instance; // 인터셉터가 설정된 axios 인스턴스 내보내기
