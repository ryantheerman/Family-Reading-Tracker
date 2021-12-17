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
    <p id="bookInfo" v-if="$store.state.books == 0">This is where you will see the books you’ve added to your reading list! Fill this page up with any books that you want to read.</p>
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

<style scoped>

.book-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 175vh;
  height: auto;
  position: relative;
  flex-direction: row;
  border-top: 2px solid rgba(162, 94, 73, 0.5);
  padding-bottom: 105px;
}

#bookInfo{
  color: rgba(162, 94, 73);
  font-size: 24px;
  margin: 200px;
  text-align: center;
}
</style>