<script setup>
const props = defineProps({ username: String });
const avatar = `/api/member/${props.username}/avatar`; // 동적 아바타 생성
</script>

<template>
  <li class="nav-item">
    <router-link class="nav-link" to="/auth/profile">
      <img :src="avatar" class="avatar avatar-sm" />
      {{ username }}
    </router-link>
  </li>
</template>
