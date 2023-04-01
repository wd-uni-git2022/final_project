<template>
  <q-page padding class="flex flex-center">
    <!-- content -->

    <div class="row " v-if="!editMode">
      <template :key="user.id" v-for="user in userList">
        <q-card class="my-card q-ma-lg q-pa-sm">
          <q-card-section horizontal>
            <q-img
              class="col"
              src="https://cdn.quasar.dev/img/parallax2.jpg"
            />
            <div class="q-pr-sm">
              <p>Username: {{ user.username }}</p>
              <p>Email: {{ user.email }}</p>
              <p>Id: {{ user.id }}</p>
            </div>

            <q-card-actions vertical class="justify-around">
              <q-btn flat round color="primary" icon="edit" @click="editUser(user)"/>
              <q-btn flat round color="red" icon="delete" @click="deleteUser(user.id)"/>
            </q-card-actions>
          </q-card-section>
        </q-card>
      </template>
    </div>


    <q-dialog v-model="editMode" persistent>
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">New username</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            filled
            v-model="currentUserEdit.username"
            label="Username"
            lazy-rules
            :rules="[ val => val && val.length > 0 || 'Please enter a valid username']"
          />
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="Update" v-close-popup @click="updateUser"/>
        </q-card-actions>
      </q-card>
    </q-dialog>
    <!--
    <div v-else>
      <q-form @submit="updateUser">
        <q-input
          filled
          v-model="currentUserEdit.username"
          label="Username"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Please enter a valid username']"
        />

        <q-btn label="Update" type="submit" color="primary"/>

      </q-form>
    </div>
-->
  </q-page>
</template>

<script setup>


import {onMounted, ref} from "vue";
import {api} from "boot/axios";
import {useRouter} from "vue-router";

const router = useRouter();

const userList = ref([]);
const editMode = ref(false);

onMounted(() => {
  console.log("user list page mounted");
  api.get("/api/user").then((response) => {
    console.log(response.data);
    userList.value = response.data;
  }).catch((error) => {
    console.log(error);
    userList.value = [{id: 1, username: "testuser"}, {id: 2, username: "testadmin"}];
  })
})


function deleteUser(userId) {

  window.location ="/user-list";
}

const currentUserEdit = ref(null);

function editUser(user) {
  editMode.value = true;
  currentUserEdit.value = user;

}

function updateUser() {
  let updateUser = currentUserEdit.value;
  api.patch("/api/user/" + currentUserEdit.value.id, updateUser)
    .then((response) => {
      // router.push("/user-list")
      // window.location ="/user-list";
      currentUserEdit.value = JSON.parse(JSON.stringify(currentUserEditDisplay.value));
    }).catch((error) => {
    // router.push("/user-list")
    // window.location ="/user-list";
  })




}

</script>
