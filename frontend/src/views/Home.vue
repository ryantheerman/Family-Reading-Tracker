<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div id="nav">
      <router-link v-bind:to="{name: 'create-family'}" class="create-family"> Parent Page </router-link>
      &nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'add-prize'}" class="add-prize"> Add a Prize </router-link>
    </div>
    
  
    <search-bar />
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
export default {
  components: { SearchBar },
  name: "home"
};
</script>
