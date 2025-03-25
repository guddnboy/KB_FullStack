<template>
  <div>
    이름: <input type="text" v-model="name" />
    <button @click="$emit('nameChanged1', { name })">이벤트 발신</button>
  </div>
</template>
<script>
export default {
  name: 'InputName',
  emits: {
    namedChanged: (e) => {
      return e.name && typeof e.name === 'string' && e.name.trim().length >= 3
        ? true
        : false;
    },
  },
  data() {
    return { name: '' };
  },
};
</script>
