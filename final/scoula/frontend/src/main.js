import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.css'; // Bootstrap CSS 추가

import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';

const app = createApp(App);

app.use(createPinia()); // 상태 관리
app.use(router); // 라우팅
app.mount('#app');
