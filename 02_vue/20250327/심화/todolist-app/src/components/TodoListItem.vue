<template>
  <li
    :key="todoitem.id"
    class="list-group-item d-flex align-items-center justify-content-between border-0 shadow-sm mb-2 rounded"
    style="background-color: #fffbe6">
    <div class="d-flex align-items-center">
      <input
        type="checkbox"
        class="form-check-input me-3"
        v-model="todoitem.completed"
        @click="toggleCompletedHandler(todoitem.id)" />

      <span
        class="flex-grow-1 fw-bold"
        :class="{
          'text-decoration-line-through text-muted': todoitem.completed,
        }"
        style="font-size: 1.1rem; color: #333">
        {{ todoitem.todo }}
      </span>
    </div>

    <button
      class="btn btn-sm text-white fw-bold"
      style="background-color: #ff8a3d; border-color: #ff8a3d"
      @click.stop="deleteTodoHandler(todoitem.id)">
      삭제
    </button>
  </li>
</template>
<script setup>
const props = defineProps({
  todoitem: { type: Object, required: true },
});
const emit = defineEmits(['toggle-completed', 'delete-todo']);

const toggleCompletedHandler = (id) => {
  emit('toggle-completed', id);
};

const deleteTodoHandler = (id) => {
  emit('delete-todo', id);
};
// export default {
//   name: 'TodoListItem',

//   // 부모로부터 전달 받은 데이터 수신 + 유효성 검사
//   props: {
//     todoitem: { type: Object, required: true },
//   },
//   methods: {
//     toggleCompletedHandler(id) {
//       this.$emit('toggle-completed', id);
//     },
//     deleteTodoHandler(id) {
//       this.$emit('delete-todo', id);
//     },
//   },

//   // 부모 컴포넌트로 발산되는 이벤트 유효성 검사
//   // (보통 특정 이벤트가 발신되는게 맞는지 확인하는 용도로 사용)
//   emits: ['toggle-completed', 'delete-todo'],
// };
</script>
