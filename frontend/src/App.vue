<template>
  <router-view/>
</template>

<script>
import {defineComponent} from 'vue'
import {api} from "boot/axios";

export default defineComponent({
  name: 'App',

  beforeMount() {
    console.log("beforeMount App.vue");
    let token = localStorage.getItem("token");
    if (token && token !== "") {
      console.log("token found in localStorage");
      api.defaults.headers.common["Authorization"] = "Bearer " + token;
    }

  }

})


</script>
