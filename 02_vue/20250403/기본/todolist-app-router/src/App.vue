<template>
  <!-- 전체 컨테이너를 감싸는 div -->
  <div class="container">
    <!-- 헤더 컴포넌트를 표시 -->
    <Header />

    <!-- 라우터에 의해 매칭되는 컴포넌트를 표시하는 영역 -->
    <!-- 클릭된 RouterLink에 따라 표시되는 컴포넌트가 변경됨 -->
    <RouterView />

    <!-- 로딩 중일 때만 Loading 컴포넌트를 표시 -->
    <Loading v-if="states.isLoading" />
  </div>
</template>

<script setup>
import { reactive, computed, provide } from 'vue';
import Header from '@/components/Header.vue';
import Loading from '@/components/Loading.vue';
import axios from 'axios';

// axios 요청 주소
const BASEURI = '/api/todos';

// 상태 변수를 reactive로 선언하여 반응형으로 만듦
const states = reactive({ todoList: [] });

/********* axios 요청 처리 함수 *********/

/**
 * TodoList 목록을 서버에서 가져오는 함수
 * @returns {Promise<void>}
 */
const fetchTodoList = async () => {
  states.isLoading = true;
  try {
    const response = await axios.get(BASEURI);
    if (response.status === 200) {
      states.todoList = response.data;
    } else {
      alert('데이터 조회 실패');
    }
  } catch (error) {
    alert('에러발생:' + error);
  } finally {
    states.isLoading = false;
  }
};

/**
 * TodoItem 서버 추가 함수수
 * @param {Object} todo - TodoItem의 정보를 포함하는 객체
 * @param {Function} successCallback - 요청 성공 시 호출될 콜백 함수
 */
const addTodo = async ({ todo, desc }, successCallback) => {
  states.isLoading = true;
  try {
    const payload = { todo, desc };
    const response = await axios.post(BASEURI, payload);
    if (response.status === 201) {
      states.todoList.push({ ...response.data, done: false });
      successCallback();
    } else {
      alert('Todo 추가 실패');
    }
  } catch (error) {
    alert('에러발생:' + error);
  } finally {
    states.isLoading = false;
  }
};

/**
 * TodoItem 서버 수정 함수
 * @param {Object} todo - TodoItem의 정보를 포함하는 객체
 * @param {Function} successCallback - 요청 성공 시 호출될 콜백 함수
 */
const updateTodo = async ({ id, todo, desc, done }, successCallback) => {
  // 로딩 스피너가 보이도록 상태값 변경
  states.isLoading = true;

  try {
    const payload = { id, todo, desc, done };
    const response = await axios.put(BASEURI + `/${id}`, payload);
    if (response.status === 200) {
      let index = states.todoList.findIndex((todo) => todo.id === id);
      states.todoList[index] = payload;
      successCallback();
    } else {
      alert('Todo변경실패');
    }
  } catch (error) {
    alert('에러발생:' + error);
  } finally {
    // 로딩 스피너가 보이지 않도록 상태값 변경
    states.isLoading = false;
  }
};

/**
 * TodoItem 서버 삭제 함수
 * @param {string} id - TodoItem의 id
 */
const deleteTodo = async (id) => {
  states.isLoading = true;
  try {
    const response = await axios.delete(BASEURI + `/${id}`);
    console.log(response.status, response.data);
    if (response.status === 200) {
      let index = states.todoList.findIndex((todo) => todo.id === id);
      states.todoList.splice(index, 1);
    } else {
      alert('Todo 삭제 실패');
    }
  } catch (error) {
    alert('에러발생:' + error);
  } finally {
    states.isLoading = false;
  }
};

/**
 * TodoItem 완료여부 토글 함수
 * @param {string} id - TodoItem의 id
 */
const toggleDone = async (id) => {
  states.isLoading = true;
  try {
    let todo = states.todoList.find((todo) => todo.id === id);
    let payload = { ...todo, done: !todo.done };
    const response = await axios.put(BASEURI + `/${id}`, payload);
    if (response.status === 200) {
      todo.done = payload.done;
    } else {
      alert('Todo 완료 변경 실패');
    }
  } catch (error) {
    alert('에러발생 :' + error);
  } finally {
    states.isLoading = false;
  }
};

/**
 * provide를 사용하여 자식 컴포넌트들에게 데이터와 함수들을 전달함
 * @param {string} name - 전달할 데이터의 이름
 * @param {Function} data - 전달할 데이터
 */
provide(
  'todoList',
  computed(() => states.todoList)
);

provide('actions', {
  addTodo,
  deleteTodo,
  toggleDone,
  updateTodo,
  fetchTodoList,
});

/* 앱이 시작될 때 TodoList를 가져옴 */
fetchTodoList();
</script>
