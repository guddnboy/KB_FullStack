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
<script>
import InputTodo from './components/InputTodo.vue';
import TodoList from './components/TodoList.vue';
import TodoCount from './components/TodoCount.vue';

export default {
  components: { InputTodo, TodoList, TodoCount },
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
    deleteTodo(id) {
      let index = this.todolist.findIndex((item) => id === item.id);
      this.todolist.splice(index, 1);
    },

    toggleCompleted(id) {
      let index = this.todolist.findIndex((item) => id === item.id);
      this.todolist[index].completed = !this.todolist[index].completed;
    },
  },
  // computed : data가 변할 때 자동으로 계산

  computed: {
    // 정렬
    sortedList() {
      this.todolist.sort((t1, t2) => {
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
      return this.todolist;
    },
    // 완료 개수 카운트
    completedCount() {
      // todolist의 요소 중 completed 가 true인 요소만 남긴 배열 반환
      return this.todolist.filter((todo) => todo.completed).length;
    },
  },
};
</script>
