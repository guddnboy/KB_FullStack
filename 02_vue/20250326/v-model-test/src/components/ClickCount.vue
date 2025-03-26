<template>
  <div>
    <h1>자식 Click Count : {{ cnt }}</h1>
    <!-- <button @click="increment">클릭 시 1 증가</button> -->
  </div>
  <ChildClickCount />
</template>

<script>
import ChildClickCount from './ChildClickCount.vue';

export default {
  name: 'ClickCount',
  components: { ChildClickCount },
  // provide로 제공된 정보 삽입하기
  inject: ['cnt'],
};
</script>
