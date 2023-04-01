<template>
  <q-page padding class="flex flex-center">
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
        :type="isPwd ? 'password' : 'text'"
        lazy-rules
        :rules="[ val => val && val.length >= 10 || 'Please enter a password with at least 10 characters']"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <q-input
        filled
        v-model="passwordConfirm"
        label="Confirm password"
        :type="isPwd2 ? 'password' : 'text'"
        lazy-rules
        :rules="[ val => val && val.length >= 10 && val === password || 'Password doesn\'t match']"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd2 ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd2 = !isPwd2"
          />
        </template>
      </q-input>

      <div>
        <q-btn label="Register" type="submit" color="primary"/>
      </div>
    </q-form>
  </q-page>
</template>

<script setup>

import {ref} from "vue";
import {api} from "boot/axios";
import {useUserStore} from "stores/user";
import {useRouter} from "vue-router";


const username = ref("")
const email = ref("")
const password = ref("")
const passwordConfirm = ref("")

const isPwd = ref(true)
const isPwd2 = ref(true)

const router = useRouter();

function onSubmit() {
  console.log("form submit");
  api.post("/api/auth/register",
    {
      "username": username.value,
      "email": email.value,
      "password": password.value
    }).then((response) => {
    console.log("response: " + response.data);
    localStorage.setItem("token", response.data.token);
    api.defaults.headers.common["Authorization"] = "Bearer " + response.data.token;
    useUserStore().isAuthenticated.value = true;
    router.push("/")
  }).catch((error) => {
    console.log(error);
  })

}
</script>
