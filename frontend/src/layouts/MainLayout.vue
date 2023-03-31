<template>
  <q-layout view="hHh Lpr fFf"> <!-- Be sure to play with the Layout demo on docs -->

    <!-- (Optional) The Header -->
    <q-header elevated>
      <q-toolbar v-if="isMobile">
        <q-btn
          flat
          round
          dense
          icon="menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title>
          QuizApp
        </q-toolbar-title>
      </q-toolbar>

      <q-tabs v-if="!isMobile">

        <q-route-tab
          icon="map"
          to="/"
          replace
          label="Home"
        />
        <q-route-tab
          icon="quiz"
          to="/quizzes"
          replace
          label="Quizzes"
        />
        <q-route-tab
          icon="login"
          to="/login"
          replace
          label="Login"
        />
        <q-route-tab
          icon="app_registration"
          to="/register"
          replace
          label="Register"
        />
        <q-route-tab
          icon="assignment"
          to="/about"
          replace
          label="About"
        />


      </q-tabs>
    </q-header>


    <!-- (Optional) A Drawer; you can add one more with side="right" or change this one's side -->
    <q-drawer
      v-model="leftDrawerOpen"
      side="left"
      bordered
      class="bg-grey-2"
      elevated
      v-if="isMobile"
    >
      <!-- QScrollArea is optional -->
      <q-scroll-area class="fit q-pa-sm">
        <!-- Content here -->
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <!-- This is where pages get injected -->
      <router-view/>
    </q-page-container>

  </q-layout>
</template>

<script setup>
import {ref, onBeforeMount} from 'vue'


// name: 'LayoutName',

const leftDrawerOpen = ref(false)

const isMobile = ref();

function mobileCheck(width){
  return width < 900;
}

onBeforeMount(() => {
  isMobile.value = mobileCheck(window.innerWidth)
  window.addEventListener("resize", resizeEventHandler);

})


function resizeEventHandler(e) {
  console.log(e);
  isMobile.value = mobileCheck(window.innerWidth);
}

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value
}


</script>
