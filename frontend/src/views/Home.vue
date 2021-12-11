<template>
  <div class="home">
    <search-bar id="search-bar" />
    <reading-list id="reading-list" />
    <activity-list />
  </div>
</template>

<script>
import SearchBar from "../components/SearchBar.vue";
import ReadingList from "../components/ReadingList.vue";
import ActivityList from '../components/ActivityList.vue';
export default {
  components: { SearchBar, ReadingList, ActivityList },
  name: "home",

};

</script>

<style>
.home {
    display: grid;
    grid-template-columns: 3fr 1fr;
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