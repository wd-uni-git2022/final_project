<template>

  <div class="flex flex-center row">
    <template v-for="quiz in quizzes" :key="quiz.quizId" >
      <quiz-list-preview class="q-ma-md" :name="quiz.quizName" :id="quiz.quizId"></quiz-list-preview>
    </template>
  </div>
</template>

<script setup>

import {onMounted, ref} from "vue";
import {api} from "boot/axios";
import QuizListPreview from "components/QuizListPreview.vue";

const quizzes = ref([]);
onMounted(() => {
  console.log("quiz list mounted");

  api.get("api/quiz").then((response) => {
    console.log(response.data);
    quizzes.value = response.data;
  }).catch((error) => {
    console.log(error);
  })

})
</script>
