<template>
  <div class="book-container">
    <div 
      v-for="book in $store.state.books"
      v-bind:key="book.isbn">
    <router-link :to="{name : 'bookDetails'}" >
      <div @click="mutateBook(book)">
      <book-card v-bind:book="book"/>  
      </div></router-link>
   
    </div>
    </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue";
import BackendService from "@/services/BackendService";
export default {
  components: { BookCard },
  name: "reading-list",

  created() {
    let id = this.$store.state.storedUser.id;
    BackendService.getFamilyMembersBooks(id).then((response) => {
      if (response.status === 200) {
        this.$store.commit("WIPE_BOOKS_ARRAY");
        this.$store.commit("ADD_BOOKS_TO_ARRAY", response.data);
      }
      })
  },
  methods: {
    mutateBook(book){
            this.$store.commit('SET_BOOK', book)
        }
  }
};
</script>

<style>

img{
    height:25vh;
    margin-bottom:15px;
} 

#reading-list {
    grid-area: reading-list;
}

.book-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 100%;
  position: relative;
  border-top: 2px solid rgba(162, 94, 73, 0.5)

}
</style>