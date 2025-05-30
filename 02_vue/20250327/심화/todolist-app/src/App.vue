<!-- VueInit : vue3 기본 형태 스니펫 -->

<template>
  <div class="container mt-5">
    <div class="card shadow-sm border-0 mb-4">
      <div class="card-body text-center bg-warning text-white rounded">
        <h1 class="display-6 fw-bold">🕒 TodoList App 💻</h1>
      </div>
    </div>
    <div class="card shadow-sm border-0">
      <div class="card-body">
        <InputTodo @add-todo="addTodo" />

        <!-- 카운트 컴포넌트 -->
        <div class="my-4">
          <TodoCount :todolist="sortedList" :completedCount="completedCount" />
        </div>

        <!-- 목록 컴포넌트 -->
        <div class="mt-3">
          <TodoList
            v-bind:todolist="sortedList"
            @toggle-completed="toggleCompleted"
            @delete-todo="deleteTodo" />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import InputTodo from './components/InputTodo.vue';
import TodoList from './components/TodoList.vue';
import TodoCount from './components/TodoCount.vue';
import { ref, reactive, onMounted, computed } from 'vue';

const todo = ref('');
const state = reactive({ todolist: [] });
onMounted(() => {
  state.todolist.push({
    id: Date.now(),
    todo: '자전거 타기',
    completed: false,
  });
  state.todolist.push({
    id: Date.now() + 1,
    todo: '딸과 공원 산책',
    completed: true,
  });

  state.todolist.push({
    id: Date.now() + 2,
    todo: '일요일 애견 카페',
    completed: false,
  });
  state.todolist.push({
    id: Date.now() + 3,
    todo: 'Vue 원고 집필',
    completed: false,
  });
});
const addTodo = (receiveTodo) => {
  state.todolist.push({
    id: Date.now() + Math.random(),
    todo: receiveTodo,
    completed: false,
  });
  todo.value = '';
};

const deleteTodo = (id) => {
  let index = state.todolist.findIndex((item) => id === item.id);
  state.todolist.splice(index, 1);
};

const toggleCompleted = (id) => {
  let index = state.todolist.findIndex((item) => id === item.id);
  state.todolist[index].completed = !state.todolist[index].completed;
};

const sortedList = computed(() => {
  state.todolist.sort((t1, t2) => {
    // true > false  -> 결과 : true
    if (t1.completed > t2.completed) {
      return 1; // 양수 반환 시 자리 바뀜 (t1, t2 -> t2, t1)
    }

    if (t1.completed < t2.completed) {
      return -1; // 음수 반환 시 자리 바꿈 X
    }

    // 위 if문 실행 X === completed가 같을 경우
    // -> 문자열 오름 차순 정렬

    // 모두 소문자로 변경(대소문자 구분 X)
    const todo1 = t1.todo.toLowerCase();
    const todo2 = t2.todo.toLowerCase();

    if (todo1 > todo2) return 1; // 자리 교체
    else if (todo1 === todo2) return 0; // 유지
    else return -1; // 유지
  });

  // 정렬된 todoList 반환
  return state.todolist;
});

const completedCount = computed(() => {
  return state.todolist.filter((todo) => todo.completed).length;
});
</script>
