<template>
  <li class="nav-item">
    <RouterLink :to="menu.uri" class="nav-link">{{ menu.text }}</RouterLink>
  </li>
</template>

<script setup>
const props = defineProps({
  menu: {
    type: Object,
    required: true,
  },
});
</script>
