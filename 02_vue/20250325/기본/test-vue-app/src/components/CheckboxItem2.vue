<template>
  <li><input type="checkbox" :checked="checked" />{{ name }}</li>
</template>
<script>
export default {
  name: 'CheckboxItem',
  props: {
    name: Number | String,
    name: String,
    checked: Boolean,
  },
};
</script>
