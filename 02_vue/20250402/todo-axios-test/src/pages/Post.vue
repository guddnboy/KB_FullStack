<template>
  <div class="container mt-4">
    <h1 class="text-primary">/post(할 일 추가)</h1>
    <hr />
    <h3 class="text-secondary">할 일 추가</h3>

    <form @submit.prevent="addTodo">
      <div class="mb-3">
        <label class="form-label">할 일</label>
        <input
          type="text"
          class="form-control"
          placeholder="할 일 입력"
          v-model="state.todo.todo" />
      </div>
      <div class="mb-3">
        <label class="form-label">설명</label>
        <input
          type="text"
          class="form-control"
          placeholder="설명 입력"
          v-model="state.todo.desc" />
      </div>
      <button type="submit" class="btn btn-primary">추가</button>
    </form>
  </div>
</template>

<script setup>
import axios from 'axios';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const state = reactive({
  todo: {},
});

const addTodo = async () => {
  try {
    const response = await axios.post('/api/todos', state.todo);
    const result = response.data;
    const id = result.id;

    router.push(`/get/${id}`);
  } catch (err) {
    console.error(err.message);
  }
};
</script>
