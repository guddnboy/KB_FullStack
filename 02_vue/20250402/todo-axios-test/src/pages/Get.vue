<template>
  <div class="container mt-4">
    <h1 class="text-primary">/get/:id(단건 조회)</h1>
    <hr />
    <h3 class="text-secondary">조회할 Todo의 id 입력</h3>
    <div class="mb-3">
      <input
        type="text"
        class="form-control d-inline-block w-auto"
        placeholder="id 입력"
        v-model="id" />
      <button class="btn btn-primary ms-2" @click="getTodo">조회</button>
    </div>

    <div class="mt-4">
      <h3 class="text-secondary">조회 결과</h3>
      <ul class="list-group">
        <li class="list-group-item">id : {{ state.todo.id }}</li>
        <li class="list-group-item">todo : {{ state.todo.todo }}</li>
        <li class="list-group-item">desc : {{ state.todo.desc }}</li>
        <li class="list-group-item">done : {{ state.todo.done }}</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const currentRoute = useRoute();

// console.log('currentRoute : ', currentRoute.params.id);

// 입력된 id 또는 /get/:id 파라미터에 전달된 값 콘솔에 출력
const id = ref(currentRoute.params.id);

const state = reactive({ todo: {} });

const getTodo = async () => {
  try {
    const response = await axios.get(`/api/todos/${id.value}`);
    state.todo = response.data;
  } catch (err) {
    console.error(err.message);
  }
};

onMounted(() => {
  if (currentRoute.params.id) {
    id.value = currentRoute.params.id;
    getTodo();
  }
});
</script>
