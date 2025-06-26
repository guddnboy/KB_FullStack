<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const logout = (e) => {
  // 실제 로그아웃 로직 구현 예정
  router.push('/');
};
</script>

<template>
  <a href="#" class="nav-link" @click.prevent="logout">
    <i class="fa-solid fa-right-from-bracket"></i>
    로그아웃
  </a>
</template>
