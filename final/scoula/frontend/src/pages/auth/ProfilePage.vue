<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1><i class="fa-solid fa-user-gear my-3"></i> 회원 정보</h1>

    <form @submit.prevent="onSubmit">
      <!-- 현재 아바타 및 사용자명 표시 -->
      <div class="mb-3 mt-3">
        <img :src="avatarPath" class="avatar avatar-lg me-4" />
        {{ member.username }}
      </div>

      <!-- 아바타 이미지 업로드 -->
      <div class="mb-3 mt-3">
        <label for="avatar" class="form-label">
          <i class="fa-solid fa-user-astronaut"></i> 아바타 이미지:
        </label>
        <input
          type="file"
          class="form-control"
          ref="avatar"
          id="avatar"
          accept="image/png, image/jpeg"
        />
      </div>

      <!-- 이메일 수정 -->
      <div class="mb-3 mt-3">
        <label for="email" class="form-label">
          <i class="fa-solid fa-envelope"></i> email
        </label>
        <input
          type="email"
          class="form-control"
          placeholder="Email"
          id="email"
          v-model="member.email"
        />
      </div>

      <!-- 비밀번호 검증 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          id="password"
          v-model="member.password"
        />
      </div>

      <!-- 에러 메시지 -->
      <div v-if="error" class="text-danger">{{ error }}</div>

      <!-- 액션 버튼들 -->
      <div class="text-center">
        <button
          type="submit"
          class="btn btn-primary mt-4 me-3"
          :disabled="disableSubmit"
        >
          <i class="fa-solid fa-user-plus"></i> 확인
        </button>

        <router-link class="btn btn-primary mt-4" to="/auth/changepassword">
          <i class="fa-solid fa-lock"></i> 비밀번호 변경
        </router-link>
      </div>
    </form>
  </div>
</template>
<script setup>
import authApi from '@/api/authApi';
import { useAuthStore } from '@/stores/auth';
import { computed, reactive, ref } from 'vue';

const auth = useAuthStore();
const avatar = ref(null); // 파일 input 참조
const avatarPath = `/api/member/${auth.username}/avatar`; // 현재 아바타 경로

// 폼 데이터 관리
const member = reactive({
  username: auth.username, // 스토어에서 가져온 현재 사용자명
  email: auth.email, // 스토어에서 가져온 현재 이메일
  password: '', // 검증용 비밀번호 (사용자 입력)
  avatar: null, // 새로운 아바타 파일
});

// 수정 처리 로직
const error = ref('');
const disableSubmit = computed(() => !member.email || !member.password);

const onSubmit = async () => {
  // 1. 수정 확인
  if (!confirm('수정하시겠습니까?')) return;

  // 2. 아바타 파일 설정
  if (avatar.value.files.length > 0) {
    member.avatar = avatar.value.files[0];
  }

  try {
    // 3. API 호출로 서버 업데이트
    await authApi.update(member);

    // 4. 성공 시 상태 동기화
    error.value = '';
    auth.changeProfile(member); // 스토어 상태 업데이트
    alert('정보를 수정하였습니다.');
    member.password = ''; // 폼 비밀번호 초기화
  } catch (e) {
    // 5. 실패 시 에러 메시지 표시
    error.value = e.response.data;
  }
};
</script>
