<script setup>
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRoute, useRouter } from 'vue-router';

const cr = useRoute();
const router = useRouter();
const auth = useAuthStore();

// 폼 데이터 관리
const member = reactive({
  username: '',
  password: '',
});

const error = ref('');
const disableSubmit = computed(() => !(member.username && member.password));

// 로그인 로직
const login = async () => {
  try {
    // 인증 스토어를 통한 로그인 처리
    await auth.login(member);
    //router.push('/'); // 성공 시 홈페이지로 이동

    // 리다이렉트 로직
    if (cr.query.next) {
      // 원래 접근하려던 페이지가 있는 경우
      router.push({ name: cr.query.next });
    } else {
      // 일반 로그인인 경우 메인 페이지로
      router.push('/');
    }
  } catch (e) {
    error.value = e.response.data;
  }
};
</script>

<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-right-to-bracket"></i>
      로그인
    </h1>

    <form @submit.prevent="login">
      <!-- 사용자 ID 입력 -->
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i> 사용자 ID:
        </label>
        <input
          type="text"
          class="form-control"
          placeholder="사용자 ID"
          v-model="member.username"
        />
      </div>

      <!-- 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          v-model="member.password"
        />
      </div>

      <!-- 에러 메시지 표시 -->
      <div v-if="error" class="text-danger">{{ error }}</div>

      <!-- 로그인 버튼 -->
      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-right-to-bracket"></i> 로그인
      </button>
    </form>
  </div>
</template>
