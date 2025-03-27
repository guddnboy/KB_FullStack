<template>
  <div class="row mb-3">
    <div class="col-md text-center">
      <div class="p-3 border rounded shadow-sm">
        <div class="text-muted">전체 Todo</div>
        <div class="fs-4 fw-bold">{{ todolist.length }}</div>
      </div>
    </div>
    <div class="col-md text-center">
      <div class="p-3 border rounded shadow-sm">
        <div class="text-muted">완료된 Todo</div>
        <div class="fs-4 fw-bold text-success">{{ completedCount }}</div>
      </div>
    </div>
    <div class="col-md text-center">
      <div class="p-3 border rounded shadow-sm">
        <div class="text-muted">미완료 Todo</div>
        <div class="fs-4 fw-bold text-danger">
          {{ todolist.length - completedCount }}
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
const props = defineProps({
  todolist: { type: Array, required: true },
  completedCount: { type: Number, required: true },
});
</script>
