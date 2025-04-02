<template>
  <h2>axios HTTP 통신</h2>
  <div class="container">
    <ul>
      <li v-for="todo in updatedList" :key="todo.id">
        <h3>Todo : {{ todo.todo }}</h3>
        <p>Description : {{ todo.desc }}</p>
      </li>
    </ul>
  </div>

  <div>
    <form @submit.prevent="addTodo">
      <div>
        <label>할 일</label>
        <input type="text" placeholder="할 일 입력" v-model="state.todo.todo" />
      </div>
      <div>
        <label>설명</label>
        <input type="text" placeholder="설명 입력" v-model="state.todo.desc" />
      </div>

      <button type="submit">추가</button>
    </form>
  </div>
</template>

<script setup>
import axios from 'axios';
import { onMounted, reactive, computed } from 'vue';

const state = reactive({
  todoList: [],
  todo: {},
});

const fetchTodos = async () => {
  try {
    const response = await axios.get('/api/todos');
    state.todoList = response.data;
  } catch (err) {
    console.error(err);
  }
};

const addTodo = async () => {
  try {
    await axios.post('/api/todos', state.todo);
  } catch (err) {
    console.error(err);
  }
  fetchTodos();
};

onMounted(() => {
  fetchTodos();
});

const updatedList = computed(() => state.todoList);
</script>
