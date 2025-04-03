<template>
  <!-- 페이지 제목 행 -->
  <div class="row">
    <div class="col p-3">
      <h2>할일 추가</h2>
    </div>
  </div>

  <!-- 할일 추가 폼 행 -->
  <div class="row">
    <div class="col">
      <!-- 할일 입력 필드 -->
      <div class="form-group">
        <label htmlFor="todo">할일 :</label>
        <input
          type="text"
          class="form-control"
          id="todo"
          v-model="todoItem.todo" />
      </div>

      <!-- 설명 입력 필드 -->
      <div class="form-group">
        <label htmlFor="desc">설명 :</label>
        <textarea
          class="form-control"
          rows="3"
          id="desc"
          v-model="todoItem.desc"></textarea>
      </div>

      <!-- 버튼 그룹 -->
      <div class="form-group">
        <button
          type="button"
          class="btn btn-primary m-1"
          @click="addTodoHandler">
          추 가
        </button>

        <button
          type="button"
          class="btn btn-primary m-1"
          @click="router.push('/todos')">
          취 소
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { inject, reactive } from 'vue';
import { useRouter } from 'vue-router'; // 라우터 인스턴스를 얻어오는 함수 가져오기

// 라우터 인스턴스 생성
const router = useRouter();

// 부모 컴포넌트에서 provide로 제공한 addTodo 함수를 inject로 가져옴
const { addTodo } = inject('actions');

// 할일 항목의 상태를 reactive로 선언하여 반응형으로 만듦
const todoItem = reactive({ todo: '', desc: '' });

/**
 * 할일 추가 버튼 클릭 시 실행되는 함수
 */
const addTodoHandler = () => {
  let { todo } = todoItem;

  // 할일이 비어있는지 확인
  if (!todo || todo.trim() === '') {
    alert('할일은 반드시 입력해야 합니다');
    return;
  }

  // 할일을 추가하고 성공 시 목록 페이지로 이동
  // 전개 연산자(...) : 객체 내부의 모든 프로퍼티를 풀어서 전달
  // { ...todoItem } -> todoItem 프로퍼티를 풀어서 새 객체{}로 만듦 === 객체 복사
  addTodo({ ...todoItem }, () => {
    router.push('/todos');
  });
};
</script>
