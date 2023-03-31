import {boot} from 'quasar/wrappers'
import axios from 'axios'

// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)
const api = axios.create({})
api.interceptors.response.use((response) => response, (error) => {

  // If jwt is not valid anymore, delete current token and redirect to login
  if (error.response.status === 401) {
    console.log("axios interceptor 401 forbidden -> delete current token and redirect to login");
    localStorage.removeItem("token");
    delete api.defaults.headers.common["Authorization"];

    window.location = "/login";
  }
});

export default boot(({app}) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
})

export {api}
