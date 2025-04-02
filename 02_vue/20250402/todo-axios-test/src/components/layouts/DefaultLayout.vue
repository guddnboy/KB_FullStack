<template>
  <div class="container">
    <slot name="header"></slot>
    <slot name="navbar"></slot>
  </div>

  <div class="content my-5 px-3">
    <!-- v-slot:default -->
    <slot></slot>
  </div>
</template>

<script setup></script>
