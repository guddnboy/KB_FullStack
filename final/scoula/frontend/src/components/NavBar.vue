<script setup>
import { reactive, computed } from 'vue';
import config from '@/config';
import MenuGroup from './menu/MenuGroup.vue';
import AccountMenuGroup from './menu/AccountMenuGroup.vue';

let state = reactive({ isNavShow: false });

// 반응형 네비게이션 클래스 계산
let navClass = computed(() =>
  state.isNavShow ? 'collapse navbar-collapse show' : 'collapse navbar-collapse'
);

const toggleNavShow = () => (state.isNavShow = !state.isNavShow);
</script>

<template>
  <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <div class="container-fluid">
      <router-link class="navbar-brand" to="/">
        <i class="fa-solid fa-house"></i>
        Scoula
      </router-link>

      <!-- 모바일 토글 버튼 -->
      <button class="navbar-toggler" type="button" @click="toggleNavShow">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- 네비게이션 메뉴 -->
      <div :class="navClass" id="collapsibleNavbar">
        <MenuGroup :menus="config.menus" />
        <AccountMenuGroup />
      </div>
    </div>
  </nav>
</template>
