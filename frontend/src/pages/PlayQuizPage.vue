<template>
  <q-page padding class="flex flex-center">
    <!-- content -->
    <h2>Question: {{ currentQuestion.question }}</h2>

    <div class="q-pa-md">
      <q-option-group
        :options="answerOptions"
        type="checkbox"
        v-model="group"
      />
    </div>
    <q-btn label="Next" @click="nextQuestion"/>

  </q-page>
</template>

<script setup>
import {onBeforeMount, onMounted, ref} from "vue";
import {api} from "boot/axios";
import {useRoute} from "vue-router";

const route = useRoute();

const questions = ref([]);
const currentQuestion = ref({});

const answerOptions = ref([]);
const group = ref([]);

onBeforeMount(() => {
  api.get("/api/quiz/" + route.params.id)
    .then((response) => {
      console.log(response.data)
      questions.value = response.data.questionList;
      currentQuestion.value = questions.value[0];


      currentQuestion.value.answerList.forEach((answer) => {
        answerOptions.value.push({label: answer.answer, value: answer.correct, color: "green"});
      })

    })
})

function nextQuestion() {

}

</script>
