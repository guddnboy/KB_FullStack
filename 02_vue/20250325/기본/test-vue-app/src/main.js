import { createApp } from 'vue';
import App from './App.vue';
import CheckboxItem from './components/CheckboxItem.vue';

createApp(App).component('CheckboxItem', CheckboxItem).mount('#app');
