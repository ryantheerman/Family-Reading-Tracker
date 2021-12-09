<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div id="nav">
    </div>
    <add-fam-members />
    <search-bar />
  </div>
</template>

<script>
import AddFamMembers from '../components/AddFamMembers.vue';
import SearchBar from '../components/SearchBar.vue';
export default {
  components: { SearchBar, AddFamMembers },
  name: "home"
};
</script>
