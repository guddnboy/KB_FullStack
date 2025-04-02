<template>
  <h2>axios HTTP 통신</h2>
</template>

<script setup>
import axios from 'axios';

const solution = async () => {
  const response = await axios.get('/api/todos/1');
  console.log(response.data);
};

new Promise((resolve) => {
  axios
    .get('/api/todos/')
    .then((response) => {
      console.log(response.data);
      return axios.get('/api/todos/1');
    })
    .then((response) => {
      console.log(response.data);
    });
}).catch((error) => {
  console.error(error);
});

solution();
</script>
