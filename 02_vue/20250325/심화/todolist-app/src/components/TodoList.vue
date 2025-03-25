<template>
  <div class="row">
    <div class="col">
      <ul class="list-group">
        <!-- 할 일 목록 요소 컴포넌트 -->
        <TodoListItem v-for="todo in todolist" :todoitem="todo" />
      </ul>
    </div>
  </div>
</template>
<script>
import TodoListItem from './TodoListItem.vue';
export default {
  name: 'TodoList',
  components: { TodoListItem },
  // 부모로부터 전달 받은 데이터 수신 + 유효성 검사
  props: {
    todolist: { type: Array, required: true },
  },
};
</script>
