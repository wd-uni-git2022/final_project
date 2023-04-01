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
          v-if="useUserStore().isAdmin"
          icon="assignment"
          to="/add-quiz"
          replace
          label="Add Quiz"
        />
        <q-route-tab
          v-if="useUserStore().isAdmin"
          icon="assignment"
          to="/user-list"
          replace
          label="Users"
        />
        <q-route-tab
          icon="assignment"
          to="/about"
          replace
          label="About"
        />
        <q-route-tab
          v-if="!useUserStore().isAuthenticated"
          icon="login"
          to="/login"
          replace
          label="Login"
        />
        <q-route-tab
          v-if="!useUserStore().isAuthenticated"
          icon="app_registration"
          to="/register"
          replace
          label="Register"
        />
        <q-route-tab
          v-if="useUserStore().isAuthenticated"
          icon="app_registration"
          @click="handleLogout"
          replace
          label="Logout"
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
        <q-list>

          <q-item clickable v-ripple to="/">
            <q-item-section>
              Home
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="/quizzes">
            <q-item-section>
              Quizzes
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="/add-quiz" v-if="useUserStore().isAdmin">
            <q-item-section>
              Add Quiz
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="/user-list" v-if="useUserStore().isAdmin">
            <q-item-section>
              Users
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="/about">
            <q-item-section>
              About
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="/login" v-if="!useUserStore().isAuthenticated">
            <q-item-section>
              Login
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple to="/register" v-if="!useUserStore().isAuthenticated">
            <q-item-section>
              Register
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple @click="handleLogout" v-if="useUserStore().isAuthenticated">
            <q-item-section>
              Logout
            </q-item-section>
          </q-item>


        </q-list>
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
import {useUserStore} from "stores/user";
import {api} from "boot/axios";


// name: 'LayoutName',

const leftDrawerOpen = ref(false)

const isMobile = ref();

function mobileCheck(width) {
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

function handleLogout() {
  localStorage.removeItem("token");
  delete api.defaults.headers.common["Authorization"];
  useUserStore().isAuthenticated = false;
  window.location = "/login";
}


</script>
