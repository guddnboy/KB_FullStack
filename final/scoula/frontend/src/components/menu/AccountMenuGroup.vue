<script setup>
import { computed } from 'vue';
import MenuItem from './MenuItem.vue';
import AccountMenuItem from './AccountMenuItem.vue';
import LogoutMenuItem from './LogoutMenuItem.vue';
import config from '@/config';

const { login, join } = config.accountMenus;

// 임시 로그인 상태 (추후 Pinia store로 대체)
const isLogin = computed(() => false);
const username = computed(() => '');
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
