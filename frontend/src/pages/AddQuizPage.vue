<template>
  <q-page padding class="flex flex-center">
    <q-form v-if="mode === 'addQuizNameMode'" @submit="switchToQuestions">
      <q-card class="my-card">
        <q-card-section>
          <div class="text-h6">Create a new quiz</div>
        </q-card-section>

        <q-separator/>

        <q-card-actions vertical>
          <div class="q-pa-md">
            <q-input
              filled
              v-model="quizName"
              label="Quiz name"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please enter a quiz name']"
            />
          </div>
          <q-btn label="Next" type="submit" color="primary"/>
        </q-card-actions>
      </q-card>

    </q-form>
    <q-form v-else-if="mode === 'addQuestionMode'" ref="addQuestionForm">
      <q-card class="my-card">
        <q-card-section>
          <div class="text-h6">Question #{{ questionCounter }}</div>
        </q-card-section>

        <q-separator/>

        <q-card-actions vertical>
          <div class="q-pa-md">
            <q-input
              filled
              v-model="questionName"
              label="Question"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please enter a question']"
            />
            <q-input
              filled
              v-model="answer1.text"
              label="Answer 1"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please enter an answer']"
            >
              <template v-slot:append>
                <q-checkbox v-model="answer1.correct"/>
              </template>
            </q-input>
            <q-input
              filled
              v-model="answer2.text"
              label="Answer 2"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please enter an answer']"
            >
              <template v-slot:append>
                <q-checkbox v-model="answer2.correct"/>
              </template>
            </q-input>
            <q-input
              filled
              v-model="answer3.text"
              label="Answer 3"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please enter an answer']"
            >
              <template v-slot:append>
                <q-checkbox v-model="answer3.correct"/>
              </template>
            </q-input>
            <q-input
              filled
              v-model="answer4.text"
              label="Answer 4"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please enter an answer']"
            >
              <template v-slot:append>
                <q-checkbox v-model="answer4.correct"/>
              </template>
            </q-input>
          </div>
          <div class="flex flex-center row">
            <q-btn class="q-ma-xs" label="Add another question" @click="addQuestion" color="primary"/>
            <q-btn class="q-ma-xs" label="Done" @click="done" color="primary"/>
          </div>

        </q-card-actions>
      </q-card>

    </q-form>

  </q-page>
</template>

<script setup>

import {onMounted, ref} from "vue";
import {api} from "boot/axios";
import {useUserStore} from "stores/user";
import {useRouter} from "vue-router";


const router = useRouter();

const quiz = ref({"quizName": "", "questionList": []});
const mode = ref("addQuizNameMode");
const quizName = ref("");
const questionCounter = ref(1);

function switchToQuestions() {
  mode.value = "addQuestionMode";
}

const addQuestionForm = ref(null);

const questionName = ref("");
const answer1 = ref({text: "", correct: false});
const answer2 = ref({text: "", correct: false});
const answer3 = ref({text: "", correct: false});
const answer4 = ref({text: "", correct: false});


function addQuestion() {
  addQuestionForm.value.validate().then((success) => {
    if (success) {
      let question = {};
      question.question = questionName.value;

      question.answerList = [];
      question.answerList.push({"answer": answer1.value.text, "correct": answer1.value.correct})
      question.answerList.push({"answer": answer2.value.text, "correct": answer2.value.correct})
      question.answerList.push({"answer": answer3.value.text, "correct": answer3.value.correct})
      question.answerList.push({"answer": answer4.value.text, "correct": answer4.value.correct})

      quiz.value.questionList.push(question);

      questionName.value = "";
      answer1.value = {text: "", correct: false};
      answer2.value = {text: "", correct: false};
      answer3.value = {text: "", correct: false};
      answer4.value = {text: "", correct: false};
      questionCounter.value++;
    }
  })

}

function done() {
  addQuestion();

  quiz.value.quizName = quizName.value;
  addQuestionForm.value.validate().then((success) => {
    if (success) {
      api.post("api/quiz", quiz.value).then((response) => {
        router.push("/quizzes");
      }).catch((error) => {

      })
    } else {

    }
  })

}


onMounted(() => {
  console.log("login page mounted");
})
</script>
