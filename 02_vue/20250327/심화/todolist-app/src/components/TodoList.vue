<template>
  <div class="row">
    <div class="col">
      <ul class="list-group">
        <!-- 할 일 목록 요소 컴포넌트 -->
        <TodoListItem
          v-for="todo in todolist"
          :todoitem="todo"
          class="list-group-item d-flex align-items-center justify-content-between border-0 shadow-sm mb-2 rounded"
          style="background-color: #fffbe6"
          @toggle-completed="$emit('toggle-completed', $event)"
          @delete-todo="$emit('delete-todo', $event)" />
      </ul>
    </div>
  </div>
</template>
<script setup>
import TodoListItem from './TodoListItem.vue';
const props = defineProps({
  todolist: { type: Array, required: true },
});
const emit = defineEmits(['toggle-completed', 'delete-todo']);
</script>
