<template>
  <h1>Teleport Text</h1>
  <button @click="changeModal">isModal 상태변경</button>

  <teleport to="#modal">
    <Modal v-if="isModal" />
  </teleport>

  <div>
    <InputName v-model:name="searchName" />
    <h3>검색어 : {{ searchName }}</h3>
  </div>

  <hr />
  <h1>부모 Click Count : {{ count }}</h1>

  <ClickCount />
</template>

<script>
import { computed } from 'vue';
import ClickCount from './components/ClickCount.vue';
import InputName from './components/InputName.vue';
import ChildClickCount from './components/ChildClickCount.vue';
import Modal from './components/Modal.vue';

export default {
  name: 'App',
  components: { InputName, ClickCount, ChildClickCount, Modal },
  data() {
    return { searchName: 'John', count: 0, isModal: false };
  },

  // 하위 컴포넌트에게 제공
  provide() {
    return {
      // computed 속성을 사용하여 변경된 값을 전달
      cnt: computed(() => this.count),

      // increment() 호출 시 App.vue의 count 1 증가
      increment: () => this.count++,
    };
  },
  methods: {
    changeModal() {
      this.isModal = true;
      setTimeout(() => {
        this.isModal = false;
      }, 2000);
    },
  },
};
</script>
