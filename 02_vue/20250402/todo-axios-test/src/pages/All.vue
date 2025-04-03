<template>
  <div>
    <h1>/All(모두 조회)</h1>
    <table class="table table-bordered">
      <thead>
        <th>Done</th>
        <th>ID</th>
        <th>Todo</th>
        <th>Description</th>
        <th>Delete</th>
      </thead>

      <tbody>
        <tr v-for="todo in state.todos" :key="todo.id">
          <td>
            <input
              type="checkbox"
              :checked="todo.done"
              @change="updateTodo(todo)" />
          </td>
          <td>{{ todo.id }}</td>
          <td>{{ todo.todo }}</td>
          <td>{{ todo.desc }}</td>
          <td>
            <button @click="deleteTodo(todo.id)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import axios from 'axios';
import { reactive, onMounted } from 'vue';

// 상태 변수 선언 -> 값 변경 시 반응 -> 컴포넌트 다시 랜더링
const state = reactive({ todos: [] });

const fetchTodoList = async () => {
  try {
    const response = await axios.get(`/api/todos`);
    state.todos = response.data;
  } catch (err) {
    console.error(err);
  }
};

const updateTodo = async (todo) => {
  todo.done = !todo.done;
  await axios.put(`/api/todos/${todo.id}`, todo);
};

const deleteTodo = async (id) => {
  const url = `/api/todos/${id}`;
  await axios.delete(url);
  fetchTodoList();
};

onMounted(() => {
  fetchTodoList();
});
</script>
