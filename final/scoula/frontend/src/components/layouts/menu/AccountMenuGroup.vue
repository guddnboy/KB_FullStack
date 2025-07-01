<!-- 계정 메뉴 그룹 컴포넌트 -->
<script setup>
import { computed } from 'vue';
import MenuItem from './MenuItem.vue';
import AccountMenuItem from './AccountMenuItem.vue';
import LogoutMenuItem from './LogoutMenuItem.vue';
import config from '@/config';
import { useAuthStore } from '@/stores/auth.js'; // <- 추가

const { login, join } = config.accountMenus;

const auth = useAuthStore(); // <- 추가
const isLogin = computed(() => auth.isLogin); // 로그인 상태 // <- 수정
const username = computed(() => auth.username); // 사용자명    // <- 수정
</script>

<template>
  <ul class="navbar-nav ms-auto">
    <template v-if="isLogin">
      <AccountMenuItem :username="username" />
      <LogoutMenuItem />
    </template>
    <template v-else>
      <MenuItem :menu="login" />
      <MenuItem :menu="join" />
    </template>
  </ul>
</template>
