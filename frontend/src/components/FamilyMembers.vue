<template>
  <div id="family-members-comp">
    <h2 id="your-family">Your Family:</h2>
    <div v-for="user in $store.state.familyMembers" v-bind:key="user.id" id="fam-links">
      <router-link class="username-link" :to="{ name: 'activity-list' }">
        <h3 @click="storeUser(user)">{{ user.username }}</h3>
      </router-link>
    </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";
export default {
  name: "family-members",
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