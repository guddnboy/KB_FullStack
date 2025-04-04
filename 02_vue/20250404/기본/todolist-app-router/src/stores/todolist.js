import { defineStore } from 'pinia';
import { reactive, computed } from 'vue';
import axios from 'axios';

export const useTodoListStore = defineStore('todolist', () => {
  const BASEURI = '/api/todos';
  const states = reactive({ todoList: [], isLoading: false });

  //   Getter
  const todoList = computed(() => states.todoList);
  const isLoading = computed(() => states.isLoading);

  const doneCount = computed(() => {
    const filterd = states.todoList.filter((todo) => todo.done);
    return filterd.length;
  });

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
  return {
    todoList,
    isLoading,
    doneCount,
    fetchTodoList,
    addTodo,
    updateTodo,
    deleteTodo,
    toggleDone,
  };
});
