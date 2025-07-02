<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-lock"></i>
      비밀번호 변경
    </h1>

    <form @submit.prevent="onSubmit">
      <!-- 이전 비밀번호 입력 -->
      <div class="mb-3">
        <label class="form-label">
          <i class="fa-solid fa-lock"></i>
          이전 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="이전 비밀번호"
          v-model="changePassword.oldPassword"
          @input="inputPassword"
        />
      </div>

      <!-- 새 비밀번호 입력 -->
      <div class="mb-3">
        <label class="form-label">
          <i class="fa-solid fa-lock"></i>
          새 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="새 비밀번호"
          v-model="changePassword.newPassword"
          @input="resetError"
        />
      </div>

      <!-- 새 비밀번호 확인 -->
      <div class="mb-3">
        <label class="form-label">
          <i class="fa-solid fa-lock"></i>
          새 비밀번호 확인:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="새 비밀번호 확인"
          v-model="changePassword.newPassword2"
          @input="resetError"
        />
      </div>

      <!-- 오류 메시지 표시 -->
      <div v-if="error" class="text-danger">{{ error }}</div>

      <!-- 제출 버튼 -->
      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-check"></i>
        확인
      </button>
    </form>
  </div>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
import authApi from '@/api/authApi';

const router = useRouter();
const auth = useAuthStore();

// 반응형 데이터 정의
const changePassword = reactive({
  username: auth.username, // 현재 로그인 사용자
  oldPassword: '', // 기존 비밀번호
  newPassword: '', // 새 비밀번호
  newPassword2: '', // 새 비밀번호 확인
});

// 버튼 비활성화 조건
const disableSubmit = computed(
  () =>
    !changePassword.oldPassword ||
    !changePassword.newPassword ||
    !changePassword.newPassword2
);

const error = ref('');

// 폼 제출 처리 로직
const onSubmit = async () => {
  // 1. 새 비밀번호 일치 검증
  if (changePassword.newPassword !== changePassword.newPassword2) {
    error.value = '새 비밀번호가 일치하지 않습니다.';
    return;
  }

  try {
    // 2. API 호출
    await authApi.changePassword(changePassword);
    alert('비밀번호를 수정했습니다.');

    // 3. 성공 시 프로필 페이지로 이동
    router.push({ name: 'profile' });
  } catch (e) {
    // 4. 오류 처리
    error.value = e.response.data;
  }
};
</script>
