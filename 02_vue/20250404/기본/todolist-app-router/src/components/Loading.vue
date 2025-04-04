<template>
  <!-- VueCsspin : 로딩 스피너 컴포넌트 -->
  <!-- https://github.com/stepanowon/vue-csspin -->
  <VueCsspin message="Loading" spin-style="cp-round" />
</template>

<script setup>
import { VueCsspin } from 'vue-csspin';
import 'vue-csspin/dist/vue-csspin.css';
</script>
