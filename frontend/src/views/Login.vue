<template>
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
</template>

<script>
import authService from "../services/AuthService";
// import LoginStyle from "../styles/LoginStyle.css";

export default {
  name: "login",
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
body{
	margin:0;
	color:#595d77;
	background:#c8c8c8;
    overflow-y:scroll;
    /* font:600 16px/18px 'Open Sans',sans-serif; */
    position: absolute;
    
}
*,:after,:before {
  box-sizing:border-box
  }

.text-center{
	width:100%;
	margin:auto;
	max-width:525px;
	min-height:670px;
	position:relative;
	background:url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg) no-repeat center;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
}
.form-signin{
	width:100%;
	height:100%;
	position:absolute;
	padding:90px 70px 50px 70px;
	background:rgb(141, 165, 156, .9);
}

.login-form{
	min-height:345px;
	position:relative;
	perspective:1000px;
	transform-style:preserve-3d;
}
 #username:hover,
 #password:hover{
	background:rgba(255,255,255,.2);
 }


.login-form .form-control {
  margin-bottom:15px;
	width:100%;
	color:#fff;
	display:block;
    border:none;
	padding:15px 20px;
	border-radius:25px;
	background:rgba(255,255,255,.1);
}

#sign-in-button{
	background:#c8d6d1;
    margin-top: 70px;
}

#need-account{
  margin:10px;
	content:'';
	width: 200px;
	height:9px;
	/* background:#fff; */
  color:#dfe0e9;
	position:absolute;
	/* transition:all .2s ease-in-out 0s; */
}

#need-account:hover{
  color:#fff
}
</style>
