<template>
  <div class="row mb-3">
    <div class="col">
      <div class="input-group shadow-sm rounded">
        <input
          id="msg"
          type="text"
          class="form-control border-0"
          name="msg"
          placeholder="무엇을 하실 건가요?"
          v-model.trim="todo"
          @keyup.enter="addTodoHandler"
          style="background-color: #f8f9fa" />
        <button
          class="btn btn-primary text-white fw-bold"
          @click="addTodoHandler"
          style="background-color: #ff8a3d; border-color: #ff8a3d">
          추가
        </button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue';

const props = defineProps({ type: String });
const emit = defineEmits(['add-todo']);
const todo = ref(props.content);

const addTodoHandler = () => {
  if (todo.value && todo.value.length >= 3) {
    emit('add-todo', todo.value);
    todo.value = '';
  } else {
    alert('할 일은 3글자 이상으로 작성해주세요!!');
  }
};
</script>
