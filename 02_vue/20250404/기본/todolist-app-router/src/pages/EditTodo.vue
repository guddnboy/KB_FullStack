<template>
  <!-- 페이지 제목을 표시하는 행 -->
  <div class="row">
    <div class="col p-3">
      <h2>할일 수정</h2>
    </div>
  </div>

  <!-- 할일 수정 폼을 표시하는 행 -->
  <div class="row">
    <div class="col">
      <!-- 할일 입력 필드 -->
      <div class="form-group">
        <label htmlFor="todo">할일:</label>
        <input
          type="text"
          class="form-control"
          id="todo"
          v-model="todoItem.todo" />
      </div>

      <!-- 설명 입력 필드 -->
      <div class="form-group">
        <label htmlFor="desc">설명:</label>
        <textarea
          class="form-control"
          rows="3"
          id="desc"
          v-model="todoItem.desc"></textarea>
      </div>

      <!-- 완료 여부 체크박스 -->
      <div class="form-group">
        <label htmlFor="done">완료여부 : </label>&nbsp;
        <input type="checkbox" v-model="todoItem.done" />
      </div>

      <!-- 버튼 그룹 -->
      <div class="form-group">
        <button
          type="button"
          class="btnbtn-primarym-1"
          @click="updateTodoHandler">
          수정
        </button>
        <button
          type="button"
          class="btnbtn-primarym-1"
          @click="router.push('/todos')">
          취소
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useTodoListStore } from '@/stores/todolist';

const store = useTodoListStore();
const todoList = store.todoList;

const { updateTodo } = store;

const router = useRouter();
const currentRoute = useRoute();

const matchedTodoItem = todoList.find(
  (todo) => todo.id === currentRoute.params.id
);

if (!matchedTodoItem) {
  router.push('/todos');
}

const todoItem = reactive({ ...matchedTodoItem });

const updateTodoHandler = () => {
  let { todo } = todoItem;
  if (!todo || todo.trim() === '') {
    alert('할일은 반드시 입력해야 합니다');
    return;
  }

  updateTodo({ ...todoItem }, () => {
    router.push('/todos');
  });
};
</script>
