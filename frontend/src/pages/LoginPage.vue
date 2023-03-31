<template>
  <q-page padding>
    <q-form @submit="onSubmit">
      <q-input
        filled
        v-model="email"
        label="Email"
        lazy-rules
        :rules="[ val => val && val.length > 0 && val.includes('@') || 'Please enter a valid email address']"
      />

      <q-input
        filled
        v-model="password"
        label="Password"
        :type="isPwd ? 'password' : 'text'"
        lazy-rules
        :rules="[ val => val]"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <div>
        <q-btn label="Login" type="submit" color="primary"/>
      </div>
    </q-form>
  </q-page>
</template>

<script setup>

import {ref} from "vue";
import axios from "axios";


const email = ref("")
const password = ref("")

const isPwd = ref(true)


function onSubmit() {
  console.log("form submit");
  axios.post("/api/auth/login",
    {
      "email": email.value,
      "password": password.value
    }).then((response) => {
    console.log("response: " + response.data);
  }).catch((error) => {
    console.log(error);
  })

}
</script>
