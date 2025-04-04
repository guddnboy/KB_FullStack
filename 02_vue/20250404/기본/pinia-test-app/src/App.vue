<template>
  <div id="app">
    <h1>Todo List</h1>
    <p>Done Count: {{ doneCount }}</p>
    <ul>
      <li v-for="todo in todoList" :key="todo.id">
        <input type="checkbox" v-model="todo.done" />
        {{ todo.text }} {{ todo.done ? '✓' : '✗' }}
      </li>
    </ul>
  </div>
</template>
<script setup>
import { computed } from 'vue';
import { useTodoListStore } from './stores/todoList';

const todoListStore = useTodoListStore();
const todoList = computed(() => {
  return todoListStore.todoList;
});
const doneCount = computed(() => {
  todoListStore.doneCount;
});
</script>
