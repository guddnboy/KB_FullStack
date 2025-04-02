<template>
  <div>
    <h1>/get(모두 조회)</h1>
    <hr />
    <h3>Todos 모두 조회</h3>
    <div>
      <button @click="getTodo">조회</button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';

const getTodo = async () => {
  try {
    const response = await axios.get(`/api/`);
    console.log(response.data);
  } catch (err) {
    console.error(err);
  }
};
</script>
