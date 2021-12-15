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
// import ActivityList from '../components/ActivityList.vue';
export default {
  components: { SearchBar, ReadingList  }, //ActivityList
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
}


#search-bar {
    grid-area: search-bar;
    color: black;
    background-color: rgba(162, 94, 73, 0.2);
    border-top: 2px solid rgba(162, 94, 73, 0.5)

}
label{
  margin:7px;
}

input{
  margin-bottom:15px;
	width:100%;
  color: hsl(14, 38%, 46% , 1);
	display:block;
  border:none;
	padding:15px 20px;
	border-radius:25px;
  background-color: rgba(162, 94, 73, 0.2);
  font-size: 16px;
}
/* input:hover{
  background-color: rgba(162, 94, 73, 0.3);
} */

#searchTitle,
#searchAuthor{
 
  margin:10px 0 10px 13px;
  text-align: center;
  height: 40px;
  width:140px;
  border-radius: 10px;
  background-color: hsl(14, 38%, 46% , .8);
  border:none;
  transition-duration: 0.4s;
  font-size: 18px;

}
#searchTitle:hover,
#searchAuthor:hover{
  background-color: rgba(162, 94, 73, 0.6);
}
.searchBar {
    height: 90vh;
    width: auto;
    position: relative;
    text-align: center;
    color: black;
    margin-bottom: 10vh;
    overflow-y: scroll;
}
.searchedTitle{
  color: rgba(162, 94, 73);
  font-size: 22px;
  margin-bottom:7px;

}

img{
    height:25vh;
    margin-bottom:15px;
}

#reading-list {
    grid-area: reading-list;
}

.book-container {
  display: flex;
  /* display: inline-block; */
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 100%;
  overflow-y: scroll;
  position: relative;
  height: 82vh;
  border-top: 2px solid rgba(162, 94, 73, 0.5)

}
</style>