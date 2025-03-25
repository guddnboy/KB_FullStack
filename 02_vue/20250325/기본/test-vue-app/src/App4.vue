<template>
  <div>
    <InputName :userName="userName" @nameChanged="nameChangedHandler" />
    <h2>App 데이터 : {{ userName }}</h2>
  </div>
</template>
<script>
import InputName from './components/InputName.vue';

export default {
  name: 'App4',
  components: { InputName },
  data() {
    return {
      userName: '',
    };
  },
  methods: {
    nameChangedHandler(e) {
      this.userName = e.name;
    },
  },
};
</script>
