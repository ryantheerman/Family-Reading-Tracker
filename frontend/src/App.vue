<template>
  <div id="app">
    <header class="header">
      <router-link v-bind:to="{ name: 'home' }" class="home-nav">

      <img src="@/images/logo.png" id="logo"/>

      </router-link>
    </header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="logout"> Logout</router-link>
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

