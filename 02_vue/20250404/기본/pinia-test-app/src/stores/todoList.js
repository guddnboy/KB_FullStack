import { defineStore } from 'pinia';
import { reactive } from 'vue';
import { computed } from 'vue';

export const useTodoListStore = defineStore('todoList', () => {
  const state = reactive({
    todoList: [
      { id: 1, text: 'Learn Vue.js', done: false },
      { id: 2, text: 'Learn Pinia', done: false },
      { id: 3, text: 'Build a Vue app', done: false },
      { id: 4, text: 'Deploy the app', done: false },
    ],
  });
  const doneCount = computed(() => {
    return state.todoList.filter((todo) => todo.done).length;
  });
  const todoList = computed(() => {
    return state.todoList;
  });
  return {
    todoList,
    doneCount,
  };
});
