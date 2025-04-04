<template>
  <!-- 네비게이션 바를 표시하는 nav 컴포넌트 -->
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- 앱 제목을 표시 -->
    <span class="navbar-brand ps-2">TodoList App</span>
    <!-- 모바일 화면에서 메뉴를 토글하는 버튼 -->
    <button
      class="navbar-toggler"
      type="button"
      @click="isNavShow = !isNavShow">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- 네비게이션 메뉴를 표시하는 div -->
    <div
      :class="
        isNavShow ? 'collapse navbar-collapse show' : 'collapse navbar-collapse'
      ">
      <!-- 네비게이션 링크들을 포함하는 ul -->
      <ul class="navbar-nav">
        <!-- 홈 페이지 링크 -->
        <li class="nav-item">
          <RouterLink class="nav-link" to="/">Home</RouterLink>
        </li>
        <!-- About 페이지 링크 -->
        <li class="nav-item">
          <RouterLink class="nav-link" to="/about">About</RouterLink>
        </li>
        <!-- TodoList 페이지 링크 -->
        <li class="nav-item">
          <RouterLink class="nav-link" to="/todos">TodoList</RouterLink>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue';
const isNavShow = ref(false);
</script>
