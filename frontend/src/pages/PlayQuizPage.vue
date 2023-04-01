<template>
  <q-page padding class="flex flex-center">
    <!-- content -->
    <template v-if="!done">
      <q-card class="my-card">
        <q-card-section>
          <div class="text-h6">Question</div>
          <div class="text-subtitle2">{{ currentQuestion.question }}</div>
        </q-card-section>

        <q-separator />

        <q-card-actions vertical>
          <div class="q-pa-md">
            <q-option-group
              :options="answerOptions"
              type="checkbox"
              v-model="group"
            />
          </div>
          <q-btn label="Next" @click="nextQuestion"/>
        </q-card-actions>
      </q-card>

    </template>
    <template v-else>
      <div class="text-center">
        <h2 >You answered {{ results.filter(r => r === true).length }} out of {{ questions.length }}
          questions correctly.</h2>
      </div>

    </template>
  </q-page>
</template>

<script setup>
import {onBeforeMount, onMounted, ref} from "vue";
import {api} from "boot/axios";
import {useRoute} from "vue-router";

const route = useRoute();

const questionCounter = ref(0);

const questions = ref([]);
const currentQuestion = ref({});

const answerOptions = ref([]);
const group = ref([]);

const results = ref([]);

const done = ref(false);

onBeforeMount(() => {
  api.get("/api/quiz/" + route.params.id)
    .then((response) => {
      console.log(response.data)
      questions.value = response.data.questionList;
      currentQuestion.value = questions.value[0];


      currentQuestion.value.answerList.forEach((answer) => {
        answerOptions.value.push({label: answer.answer, value: answer.answerId, color: "green"});
      })

    })
})

function nextQuestion() {

  let falseAnswerIds = currentQuestion.value.answerList.filter(a => !a.correct).map(a => a.answerId);
  console.log("falseAnswerIds: " + falseAnswerIds);
  let allAnswersCorrect = !falseAnswerIds.some(f => group.value.includes(f));

  if (allAnswersCorrect) {
    console.log("all answers correct");
  } else {
    console.log("not all answers are correct");
  }

  results.value.push(allAnswersCorrect);

  questionCounter.value++;
  if (questionCounter.value < questions.value.length) {
    currentQuestion.value = questions.value[questionCounter.value];

    answerOptions.value = [];
    currentQuestion.value.answerList.forEach((answer) => {
      answerOptions.value.push({label: answer.answer, value: answer.answerId, color: "green"});
    })

  } else {
    done.value = true;
  }

}

</script>
