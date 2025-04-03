import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../pages/Home.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),

  /* 라우팅 테이블 */
  routes: [
    /* 메인(홈) 페이지 */
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },

    /* 소개 페이지 */
    {
      path: '/about',
      name: 'about',
      component: () => import('../pages/About.vue'),
    },

    /* 할 일 목록 페이지 */
    {
      path: '/todos',
      name: 'todos',
      component: () => import('../pages/TodoList.vue'),
    },

    /* 할 일 추가 페이지 */
    {
      path: '/todos/add',
      name: 'addTodo',
      component: () => import('../pages/AddTodo.vue'),
    },

    /* 할 일 상세 페이지 */
    {
      path: '/todos/:id',
      name: 'editTodo',
      component: () => import('../pages/EditTodo.vue'),
    },

    /* 할 일 수정 페이지지 */
    {
      path: '/todos/edit/:id',
      name: 'editTodo',
      component: () => import('../pages/EditTodo.vue'),
    },

    /* 404 not found 페이지 */
    {
      path: '/:paths(.*)*',
      name: 'notFound',
      component: () => import('../pages/NotFound.vue'),
    },
  ],
});

export default router;
