<script setup>
// 컴포넌트 설정
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import authApi from '@/api/authApi';

const router = useRouter();
const avatar = ref(null); // 파일 input 참조
const checkError = ref(''); // 중복 체크 메시지
const disableSubmit = ref(true); // 제출 버튼 비활성화 상태

// 회원 정보 reactive 객체
const member = reactive({
  username: '',
  email: '',
  password: '',
  password2: '', // 비밀번호 확인
  avatar: null,
});

// 아래 코드 순서대로 추가...
// ID 중복 체크 로직
const checkUsername = async () => {
  // username 중복 체크
  if (!member.username) {
    return alert('사용자 ID를 입력하세요.');
  }

  // 중복 검사 결과
  // - true  : 중복 O(이미 사용 중, class='text-danger' , 버튼 비활성)
  // - false : 중복 X(사용 가능   , class='text-primary', 버튼 활성)
  disableSubmit.value = await authApi.checkUsername(member.username);
  checkError.value = disableSubmit.value
    ? '이미 사용중인 ID입니다.'
    : '사용 가능한 ID입니다.';
};

// username 입력 핸들러
const changeUsername = () => {
  disableSubmit.value = true; // 제출 버튼 비활성화
  checkError.value = member.username ? 'ID 중복 체크를 하셔야 합니다.' : '';
};

// 회원가입 처리
const join = async () => {
  // 이메일 입력 검증
  if (member.email.trim() === '') {
    return alert('이메일을 입력해주세요');
  }

  // 비밀번호 확인 검증
  if (member.password != member.password2) {
    return alert('비밀번호가 일치하지 않습니다.');
  }

  // 아바타 파일 설정
  if (avatar.value.files.length > 0) {
    member.avatar = avatar.value.files[0];
  }

  try {
    await authApi.create(member); // 회원가입 API 호출
    alert('가입 성공!');
    router.push({ name: 'login' }); // 성공 시 홈페이지로 이동
  } catch (e) {
    console.error(e);
  }
};
</script>

<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-user-plus"></i>
      회원 가입
    </h1>

    <form @submit.prevent="join">
      <!-- 사용자 ID -->
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i>
          사용자 ID :
          <button
            type="button"
            class="btn btn-success btn-sm py-0 me-2"
            @click="checkUsername"
          >
            ID 중복 확인
          </button>
          <span :class="disableSubmit ? 'text-danger' : 'text-primary'">{{
            checkError
          }}</span>
        </label>
        <input
          type="text"
          class="form-control"
          placeholder="사용자 ID"
          id="username"
          @input="changeUsername"
          v-model="member.username"
          required
        />
      </div>
      <!-- 아바타 이미지 파일 업로드(png,jpg 제한)  -->
      <div>
        <label for="avatar" class="form-label">
          <i class="fa-solid fa-user-astronaut"></i>
          아바타 이미지:
        </label>
        <input
          type="file"
          class="form-control"
          ref="avatar"
          id="avatar"
          accept="image/png, image/jpeg"
        />
      </div>

      <!-- 이메일 -->
      <div class="mb-3 mt-3">
        <label for="email" class="form-label">
          <i class="fa-solid fa-envelope"></i>
          email
        </label>
        <input
          type="email"
          class="form-control"
          placeholder="Email"
          id="email"
          v-model="member.email"
          required
        />
      </div>

      <!-- 비밀번호 -->
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
          required
        />
      </div>

      <!-- 비밀번호 확인 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호 확인:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호 확인"
          id="password2"
          v-model="member.password2"
          required
        />
      </div>

      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-user-plus"></i> 확인
      </button>
    </form>
  </div>
</template>
