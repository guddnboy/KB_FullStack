<template>
  <li
    :key="todoitem.id"
    class="list-group-item"
    :class="{ 'list-group-item-success': todoitem.completed }">
    <input
      type="checkbox"
      class="pointer me-3"
      v-model="todoitem.completed"
      @click="toggleCompleted(todoitem.id)" />

    <span class="pointer" :class="{ 'todo-done': todoitem.completed }">
      {{ todoitem.todo }} {{ todoitem.completed ? '(완료)' : '' }}
    </span>

    <span
      class="float-end badge bg-secondary pointer"
      @click.stop="deleteTodo(todoitem.id)"
      >삭제</span
    >
  </li>
</template>
<script>
export default {
  name: 'TodoListItem',

  // 부모로부터 전달 받은 데이터 수신 + 유효성 검사
  props: {
    todoitem: { type: Object, required: true },
  },
};
</script>
