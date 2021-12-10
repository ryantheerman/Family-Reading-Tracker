<template>
  <div class="book-container">
    <book-card
      v-bind:book="book"
      v-for="book in $store.state.books"
      v-bind:key="book.isbn"
    />
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue";
import backendService from "@/services/BackendService";
export default {
  components: { BookCard },
  name: "reading-list",

  created() {
    // get all books by user id, ie current collection, mutate into data store
    backendService.getBooks().then((response) => {
      this.$store.commit("ADD_BOOKS_TO_ARRAY", response.data);
    });

    // get requests for books, activites, prizes, family members(?)
  },
};
</script>

<style>
.book-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 100%;
  overflow-y: scroll;
  position: relative;
  height: 82vh;
}
</style>