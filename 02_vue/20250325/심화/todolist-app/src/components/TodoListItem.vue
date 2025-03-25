<template>
  <li
    :key="todoitem.id"
    class="list-group-item"
    :class="{ 'list-group-item-success': todoitem.completed }">
    <input
      type="checkbox"
      class="pointer me-3"
      v-model="todoitem.completed"
      @click="toggleCompletedHandler(todoitem.id)" />

    <span class="pointer" :class="{ 'todo-done': todoitem.completed }">
      {{ todoitem.todo }} {{ todoitem.completed ? '(완료)' : '' }}
    </span>

    <span
      class="float-end badge bg-secondary pointer"
      @click.stop="deleteTodoHandler(todoitem.id)"
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
  methods: {
    toggleCompletedHandler(id) {
      this.$emit('toggle-completed', id);
    },
    deleteTodoHandler(id) {
      this.$emit('delete-todo', id);
    },
  },

  // 부모 컴포넌트로 발산되는 이벤트 유효성 검사
  // (보통 특정 이벤트가 발신되는게 맞는지 확인하는 용도로 사용)
  emits: ['toggle-completed', 'delete-todo'],
};
</script>
