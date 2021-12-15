<template>
  <div>
    <h2>Your Family:</h2>
    <div v-for="user in $store.state.familyMembers" v-bind:key="user.id">
      <router-link :to="{ name: 'reading-list' }">
        <h3 @click="storeUser(user)">{{ user.username }}</h3>
      </router-link>
    </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";
export default {
  name: "family-to-reading-list",
  created() {
    AuthService.getFamilyMembers().then((response) => {
      this.$store.commit("ADD_USERS_TO_FAMILY_ARRAY", response.data);
    });
  },
  methods: {
      storeUser(user) {
          this.$store.commit('STORE_SELECTED_USER', user);
      }
  }
};
</script>

<style>
</style>