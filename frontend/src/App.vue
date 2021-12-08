<template>
  <div id="app">
    <header class="header">
      <h1>Family Reading Tracker</h1>
    </header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" class="home">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="logout"> &nbsp;|&nbsp; Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
import AppStyle from "@/styles/AppStyle.css";

export default {
  components: AppStyle
}
</script>

