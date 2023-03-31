<template>


  <q-card class="my-card">
    <q-card-section horizontal class="bg-primary text-white">
      <div class="text-h6">{{ props.name }}</div>

      <q-card-actions vertical class="justify-around q-px-md">
        <q-btn v-if="useUserStore().isAuthenticated" color="secondary" label="Play"/>
        <q-btn v-else color="secondary" label="Play" disable/>
      </q-card-actions>
    </q-card-section>


  </q-card>

</template>

<script setup>

import {useUserStore} from "stores/user";

const props = defineProps(["name", "id"])


</script>
