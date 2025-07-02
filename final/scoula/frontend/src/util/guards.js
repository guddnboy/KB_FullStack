import { useAuthStore } from '@/stores/auth';

export const isAuthenticated = (to, from) => {
  const auth = useAuthStore();

  // 로그인 상태가 아닌 경우
  if (!auth.isLogin) {
    console.log('로그인 필요.....');
    // 원래 접근하려던 페이지를 next 쿼리에 저장하고 로그인 페이지로 리다이렉트
    return { name: 'login', query: { next: to.name } };
  }

  console.log('로그인 인증');
  // 로그인 상태인 경우 접근 허용
};
