<template>


  <q-card class="my-card">
    <q-card-section>
      <div class="text-h6">{{ props.name }}</div>
    </q-card-section>

    <q-separator />

    <q-card-actions vertical>
      <q-btn v-if="useUserStore().isAuthenticated" color="secondary" label="Play" :to='"/quizzes/" + id'/>
      <q-btn v-else color="secondary" label="Play" disable/>
    </q-card-actions>
  </q-card>

</template>

<script setup>

import {useUserStore} from "stores/user";

const props = defineProps(["name", "id"])


</script>
