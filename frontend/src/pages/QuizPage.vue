<template>
  <q-page padding>
    <!-- content -->

  </q-page>
</template>

<script setup>
import {onMounted} from "vue";
import {api} from "boot/axios";

onMounted(() => {
  api.get("api/quiz").then((response) => {
    console.log(response.data);
  }).catch((error) => {
    console.log(error);
  })
})
</script>
