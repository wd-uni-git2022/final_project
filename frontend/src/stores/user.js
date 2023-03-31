import { defineStore } from 'pinia'
import {ref} from 'vue'


export const useUserStore = defineStore('counter', () => {
  const isAuthenticated = ref(false)



  return { isAuthenticated }
})
