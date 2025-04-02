<template>
  <h2>axios HTTP 통신</h2>
  <div class="container">
    <ul>
      <li v-for="todo in state.todos" :key="todo.id">
        <h3>{{ todo.todo }}</h3>
        <p>{{ todo.desc }}</p>
      </li>
    </ul>
  </div>
</template>

<script setup>
import axios from 'axios';
import { reactive, onMounted } from 'vue';

const state = reactive({ todos: [] });

const fetchTodos = async () => {
  try {
    const response = await axios.get('/api');
    return response.data;
  } catch (err) {
    console.error(err);
  }
};

onMounted(async () => {
  state.todos = await fetchTodos();
});
</script>
