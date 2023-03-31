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
        v-model="username"
        label="Username"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please enter a username']"
      />
      <q-input
        filled
        v-model="password"
        label="Password"
        type="password"
        lazy-rules
        :rules="[ val => val && val.length >= 10 || 'Please enter a password with at least 10 characters']"
      />
      <q-input
        filled
        v-model="passwordConfirm"
        label="Confirm password"
        type="password"
        lazy-rules
        :rules="[ val => val && val.length >= 10 && val === password || 'Password doesn\'t match']"
      />

      <div>
        <q-btn label="Submit" type="submit" color="primary"/>
      </div>
    </q-form>
  </q-page>
</template>

<script setup>

import {ref} from "vue";

const username = ref("")
const email = ref("")
const password = ref("")
const passwordConfirm = ref("")
function onSubmit(){
  console.log("form submit");
}
</script>
