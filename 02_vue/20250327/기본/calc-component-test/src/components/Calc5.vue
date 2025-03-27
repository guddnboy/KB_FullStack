<template>
  <div>
    X : <input type="text" v-model.number="x" /><br />
    결과 : {{ result }}
  </div>
</template>

<script>
import { ref, watch } from 'vue';

export default {
  name: 'Calc4',

  setup() {
    const x = ref(0);

    const result = ref(0);

    watch(x, (current, old) => {
      console.log(`현재 값 : ${current} 이전 값 : ${old}`);
      result.value = current;
    });

    return { x, result };
  },
};
</script>
