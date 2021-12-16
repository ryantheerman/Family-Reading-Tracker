<template>
  <div id="app">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Abril+Fatface&family=Montserrat&family=Readex+Pro:wght@300;304&display=swap" rel="stylesheet">
    <img src="@/images/header.png" class="header"/>
      <div id="nav" v-if="$store.state.token != ''">
        <router-link v-bind:to="{ name: 'home' }" class="home-nav">
        <img src="@/images/leggo-my-logo.png" id="logo"/>
        </router-link>
        <router-link v-bind:to="{ name: 'add-prize' }"  v-if="$store.state.token != '' && $store.state.user.isParent != false && $store.state.user.familyId != 0" class="prize">Prizes</router-link>
        <router-link v-bind:to="{ name: 'family' }" v-if="$store.state.token != '' && $store.state.user.isParent != false" class="family">Family</router-link>
        <router-link v-bind:to="{ name: 'activity' }" v-if="$store.state.token != ''" class="activity">Activity </router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="logout">Logout</router-link>
      </div>
    <router-view id="router-view"/>
  </div>
</template>
<script>
import AppStyle from "@/styles/AppStyle.css";
export default {
  component: AppStyle
}
</script>