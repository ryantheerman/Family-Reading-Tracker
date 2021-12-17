<template>
  <div class="yourfamilymembers" >
    <h2 id="your-family" v-if="$store.state['user'].familyId != 0">Your Family:</h2>
    <p v-else id="famInstructions"> 
      Start your family on this page. Starting a family will allow you, as a parent, to add your loved ones to your family account and check in with them on their reading activity. It will also allow you to create and award prizes. It all starts here with creating your family reading tracker family!
    </p>
    <div v-for="user in $store.state.familyMembers" v-bind:key="user.id">
      <router-link :to="{ name: 'reading-list' }">
        <h3 id="fam-links" @click="storeUser(user)">{{ user.username }}</h3>
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

/* .yourfamilymembers{
  grid-area: family;
} */
#famInstructions,
#your-family {
  text-align: left;
  margin-left: 20px;
  /* text-align: left; */
  color: rgb(24, 49, 78);
  }

  #famInstructions{
    margin-top:30px;
    font-size:21px;
    margin-right: 30px;
  }

#fam-links {
   text-align: center;
   border: 1px solid  rgb(24, 49, 78, .6);
   width:300px;
   height: 40px;
   border-radius:25px;
   margin:10px;
   background-color: rgb(24, 49, 78, .3);
   color:white;
   padding:7px

   /* color:white; */
}
#fam-links:hover{
  background-color: rgb(24, 49, 78, .6)

}

</style>