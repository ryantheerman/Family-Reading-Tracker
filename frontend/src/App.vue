<template>
  <div id="app">
    <header class="header">
      <h1>Family Reading Trackr</h1>
    </header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" class="home-nav">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="logout"> &nbsp;|&nbsp; Logout</router-link>
      <router-link v-bind:to="{ name: 'add-prize' }"  v-if="$store.state.token != '' && $store.state.user.isParent != false && $store.state.user.familyId != 0" class="prize"> &nbsp;|&nbsp; Prizes</router-link>
      <router-link v-bind:to="{ name: 'family' }" v-if="$store.state.token != '' && $store.state.user.isParent != false" class="create-family"> &nbsp;|&nbsp; Family</router-link>
      <router-link v-bind:to="{ name: 'activity' }" v-if="$store.state.token != ''"> &nbsp;|&nbsp; Activity </router-link>
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