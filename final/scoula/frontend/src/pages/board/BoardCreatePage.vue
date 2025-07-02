<template>
  <h1><i class="fa-regular fa-pen-to-square"></i> 글 작성</h1>

  <form @submit.prevent="submit">
    <!-- 제목 입력 -->
    <div class="mb-3 mt-3">
      <label for="title" class="form-label">제목</label>
      <input
        type="text"
        class="form-control"
        placeholder="제목"
        id="title"
        v-model="article.title"
      />
    </div>

    <!-- 첨부파일 선택 -->
    <div class="mb-3 mt-3">
      <label for="files" class="form-label">첨부파일</label>
      <input
        type="file"
        class="form-control"
        placeholder="첨부파일"
        id="files"
        ref="files"
        multiple
      />
    </div>

    <!-- 내용 입력 -->
    <div class="mb-3 mt-3">
      <label for="content" class="form-label">내용</label>
      <textarea
        class="form-control"
        placeholder="내용"
        id="content"
        v-model="article.content"
        rows="10"
      ></textarea>
    </div>

    <!-- 버튼 영역 -->
    <div class="my-5 text-center">
      <button
        type="submit"
        class="btn btn-primary me-3"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-check"></i> 확인
      </button>
      <router-link class="btn btn-primary" :to="{ name: 'board/list' }">
        <i class="fa-solid fa-list"></i> 목록
      </router-link>
    </div>
  </form>
</template>
<script setup>
import boardApi from '@/api/boardApi';
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRoute, useRouter } from 'vue-router';

const auth = useAuthStore();
const router = useRouter();
const cr = useRoute();

const files = ref(null); // 파일 input 참조

// 게시글 데이터
const article = reactive({
  writer: auth.username, // 로그인된 사용자명
  title: '',
  content: '',
  files: null,
});

// 제목이 입력된 경우에만 제출 버튼 활성화
const disableSubmit = computed(() => !article.title);

// 폼 제출 처리
const submit = async () => {
  if (!confirm('등록할까요?')) return;

  // 첨부파일 선택이 있는 경우
  if (files.value.files.length > 0) {
    article.files = files.value.files;
  }

  await boardApi.create(article);
  router.push('/board/list');
};
</script>
