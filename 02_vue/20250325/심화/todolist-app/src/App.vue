<!-- VueInit : vue3 기본 형태 스니펫 -->

<template>
  <div class="container">
    <div class="card card-body bg-light">
      <div class="title">🕒 TodoList App 💻</div>
    </div>
    <div class="card card-default card-borderless">
      <div class="card-body">
        <InputTodo @add-todo="addTodo" />

        <!-- 카운트 컴포넌트 -->

        <!-- 목록 컴포넌트 -->
        <TodoList v-bind:todolist="todolist" />
      </div>
    </div>
  </div>
</template>
<script>
import InputTodo from './components/InputTodo.vue';
import TodoList from './components/TodoList.vue';

export default {
  components: { InputTodo, TodoList },
  data() {
    return {
      todolist: [
        { id: Date.now(), todo: '자전거 타기', completed: false },
        { id: Date.now() + 1, todo: '딸과 공원 산책', completed: true },
        {
          id: Date.now() + 2,
          todo: '일요일 애견 카페',
          completed: false,
        },
        { id: Date.now() + 3, todo: 'Vue 원고 집필', completed: false },
      ],
    };
  },
  methods: {
    // receiveTodo : InputTodo 컴포넌트에서 전달받은 Todo
    // this.#emit('add-todo', this.todo); 에서 전달된 this.todo(payload)

    addTodo(receiveTodo) {
      this.todolist.push({
        id: Date.now() + Math.random(),
        todo: receiveTodo,
        completed: false,
      });
      this.todo = '';
    },
  },
};
</script>
