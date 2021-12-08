<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div id="nav">
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
