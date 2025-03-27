<template>
  <div>
    X : <input type="text" v-model="x" /><br />
    Y : <input type="text" v-model="y" /><br />
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'Calc',

  setup() {
    const x = ref(10);
    const y = ref(20);
    return { x, y };
  },
};
</script>
