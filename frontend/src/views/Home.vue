<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
<<<<<<< HEAD
=======
    <div id="nav">
    </div>
    <add-fam-members />
>>>>>>> 6ebcdd0fee715143a1bb67bf16f12f588cafff40
    <search-bar />
  </div>
</template>

<script>
import AddFamMembers from '../components/AddFamMembers.vue';
import SearchBar from '../components/SearchBar.vue';
export default {
<<<<<<< HEAD
  components: {SearchBar},
=======
  components: { SearchBar, AddFamMembers },
>>>>>>> 6ebcdd0fee715143a1bb67bf16f12f588cafff40
  name: "home"
};
</script>
