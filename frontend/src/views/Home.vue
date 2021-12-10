<template>
  <div class="home">
    <h1>Home</h1>
    <search-bar id="search-bar" />
    <reading-list id="reading-list" />
    
  </div>
</template>

<script>
import SearchBar from "../components/SearchBar.vue";
import ReadingList from "../components/ReadingList.vue";
export default {
  components: { SearchBar, ReadingList },
  name: "home",

};

</script>

<style>
.home {
    display: grid;
    grid-template-columns: 3fr 1fr;
    grid-template-rows: ;
    grid-template-areas: "reading-list search-bar";
    column-gap: 20px;
}

#search-bar {
    grid-area: search-bar;
}

#reading-list {
    grid-area: reading-list;
}
</style>
