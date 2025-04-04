<template>
  <!-- 전체 컨테이너를 감싸는 div -->
  <div class="container">
    <!-- 헤더 컴포넌트를 표시 -->
    <Header />

    <!-- 라우터에 의해 매칭되는 컴포넌트를 표시하는 영역 -->
    <!-- 클릭된 RouterLink에 따라 표시되는 컴포넌트가 변경됨 -->
    <RouterView />

    <!-- 로딩 중일 때만 Loading 컴포넌트를 표시 -->
    <Loading v-if="isLoading" />
  </div>
</template>

<script setup>
import Header from '@/components/Header.vue';
import Loading from '@/components/Loading.vue';
import { useTodoListStore } from './stores/todolist';
import { computed } from 'vue';

const store = useTodoListStore();
const isLoading = computed(() => store.isLoading);
const { fetchTodoList } = store;

fetchTodoList();
</script>
