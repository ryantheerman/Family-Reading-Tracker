<template>
<div class="login-container">
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal" id="h1">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div class="login-form">
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <router-link :to="{ name: 'register' }" id="need-account">Need an account?</router-link>
        <button type="submit" id="sign-in-button" class="form-control">Sign in</button>
      </div>
    </form>
  </div>
    <p id="readMe" class="readMe" >Welcome to ReadME!
ReadME is a family reading tracker designed for families that want to connect to a good story and be connected with each other. Throughout this app, you will have the ability to start a family, search for your favorite books, and compete for prizes. 
We believe that coming together as a family and bonding over storytelling is a great way to stay connected and promote a fun way to read more books!</p>
 
</div>
</template>

<script>
import authService from "../services/AuthService";
import LoginStyle from "../styles/LoginStyle.css";

export default {
  name: "login",
  component: LoginStyle,
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

</style>
