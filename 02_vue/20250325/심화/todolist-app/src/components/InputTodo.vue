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
<script>
export default {
  name: 'InputTodo',
  data() {
    return { todo: '' };
  },
  methods: {
    // 추가 동작
    addTodoHandler() {
      if (this.todo.length >= 3) {
        // 작성된 할 일 (this.todo)을
        // 부모 컴포넌트 (App.vue)로 전달하기!
        // -> 이벤트 발신(emit) 이용하기!
        this.$emit('add-todo', this.todo);
        this.todo = '';
      } else {
        alert('할 일은 3글자 이상으로 작성해주세요!!');
      }
    },
  },
};
</script>
