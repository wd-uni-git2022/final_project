<template>
  <router-view/>
</template>

<script setup>
import {defineComponent, onBeforeMount} from 'vue'
import {api} from "boot/axios";
import {useUserStore} from "stores/user";
import jwtDecode from "jwt-decode";
/**
export default defineComponent({
  name: 'App',

  beforeMount() {
    console.log("beforeMount App.vue");
    let token = localStorage.getItem("token");
    if (token && token !== "") {
      console.log("token found in localStorage");
      api.defaults.headers.common["Authorization"] = "Bearer " + token;
      useUserStore().isAuthenticated = true;
    }

  }

})
**/
const name = "App";

onBeforeMount(() => {
  console.log("beforeMount App.vue");
  let token = localStorage.getItem("token");
  if (token && token !== "") {
    console.log("token found in localStorage");
    api.defaults.headers.common["Authorization"] = "Bearer " + token;
    useUserStore().isAuthenticated = true;
    let decodedToken = jwtDecode(token);
    let hasAdminRole = decodedToken.groups[0] === "Admin";
    useUserStore().isAdmin = hasAdminRole;
  }
})

</script>
