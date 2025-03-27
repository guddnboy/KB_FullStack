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
</script>
