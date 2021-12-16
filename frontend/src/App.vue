<template>
  <div id="app">
    <h1 class="header">ReadMe</h1>
    
      <div id="nav" v-if="$store.state.token != ''">
        <!-- <router-link :to="{ name: 'test-activity' }">TestActivity</router-link> -->
        <router-link v-bind:to="{ name: 'home' }" class="home-nav">
        <img src="@/images/logo.png" id="logo"/>
        </router-link>
        <router-link v-bind:to="{ name: 'add-prize' }"  v-if="$store.state.token != '' && $store.state.user.isParent != false && $store.state.user.familyId != 0" class="prize">Prizes</router-link>
        <router-link v-bind:to="{ name: 'family' }" v-if="$store.state.token != '' && $store.state.user.isParent != false" class="family">Family</router-link>
        <router-link v-bind:to="{ name: 'test-activity' }" v-if="$store.state.token != ''" class="activity">Activity </router-link>
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