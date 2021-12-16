<template>
  <div class="home">
    
    <search-bar id="search-bar" />
    
    <reading-list id="reading-list" />
    <!-- <activity-list id="activity-list"/> -->
  </div>
</template>

<script>
import SearchBar from "../components/SearchBar.vue";
import ReadingList from "../components/ReadingList.vue";
import HomeStyles from "@/styles/HomeStyle.css";
// import ActivityList from '../components/ActivityList.vue';
export default {
  component: HomeStyles,
  components: { SearchBar, ReadingList}, //ActivityList
  name: "home",
  created(){
      this.$store.state.storedUser = this.$store.state['user']
    
  }
};
</script>
<style>

.home {
    display: grid;
    grid-template-columns: 3fr 1fr;
    grid-template-areas: "reading-list search-bar";
   background-color: rgba(162, 94, 73, 0.2);

}

#search-bar {
    grid-area: search-bar;
    color: black;
    border-top: 2px solid rgba(162, 94, 73, 0.5);

}
</style>
