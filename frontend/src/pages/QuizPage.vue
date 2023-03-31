<template>
  <q-page padding class="flex flex-center">
    <!-- content -->
    <quiz-list></quiz-list>
  </q-page>
</template>

<script setup>

import QuizList from "components/QuizList.vue";
import {onMounted} from "vue";

onMounted(() => {
  console.log("quiz page mounted");
})

</script>
