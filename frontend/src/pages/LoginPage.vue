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
        v-model="password"
        label="Password"
        :type="isPwd ? 'password' : 'text'"
        lazy-rules
        :rules="[ val => val && val.length > 0|| 'Please enter your password']"
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

import {onMounted, ref} from "vue";
import {api} from "boot/axios";
import {useUserStore} from "stores/user";
import {useRouter} from "vue-router";
import jwtDecode from "jwt-decode";


const email = ref("")
const password = ref("")

const isPwd = ref(true)

const router = useRouter();

function onSubmit() {
  console.log("form submit");
  api.post("/api/auth/login",
    {
      "email": email.value,
      "password": password.value
    }).then((response) => {
    console.log("response: " + response.data);
    localStorage.setItem("token", response.data.token);
    api.defaults.headers.common["Authorization"] = "Bearer " + response.data.token;
    useUserStore().isAuthenticated = true;

    let decodedToken = jwtDecode(response.data.token);
    let hasAdminRole = decodedToken.groups[0] === "Admin";
    useUserStore().isAdmin = hasAdminRole;

    router.push("/")
  }).catch((error) => {
    console.log(error);
  })

}

onMounted(() => {
  console.log("login page mounted");
})
</script>
