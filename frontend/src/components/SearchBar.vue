<template>
  <div>
    <label for="Search bar"
      >
    </label>
    <input
      @keydown.enter="getBooks"
      type="text"
      id="Search bar"
      name="Search bar"
      placeholder="Search Books:"
      v-model="searchTerm"
    />
    <div class="book-search-buttons">
      <button id="searchTitle" @click="getBooksByTitle">Search by Title</button>
      <button id="searchAuthor" @click="getBooksByAuthor">Search by Author</button>
    </div>
    <div class="searchBar">
      <div v-for="book in booksArray" v-bind:key="book.isbn">
        <router-link :to="{ name: 'bookDetails' }">
          <div @click="mutateBook(book)">
            <p v-if="book.volumeInfo.imageLinks" class="searchedTitle">{{ book.volumeInfo.title }}</p>
            <img
              v-if="book.volumeInfo.imageLinks"
              v-bind:src="book.volumeInfo.imageLinks.thumbnail"
            />
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>
<script>
import ApiService from "@/services/ApiService.js";
export default {
  data() {
    return {
      searchTerm: "",
      booksArray: [],
    };
  },
  methods: {
    getBooksByTitle() {
      ApiService.getByTitle(this.searchTerm).then((response) => {
        this.booksArray = response.data.items;
      });
    },
    getBooksByAuthor() {
      ApiService.getByAuthor(this.searchTerm).then((response) => {
        this.booksArray = response.data.items;
      });
    },
    mutateBook(book) {
      let sentBook = {
          author: ''
      };
      
      sentBook.isbn = book.volumeInfo.industryIdentifiers[0].identifier;
      sentBook.title = book.volumeInfo.title;

      book.volumeInfo.authors.forEach((x) => {
        sentBook.author += x + " ";
      });

      sentBook.description = book.volumeInfo.description;
      sentBook.pageCount = book.volumeInfo.pageCount;
      sentBook.thumbnail = book.volumeInfo.imageLinks.thumbnail;
      this.$store.commit("SET_BOOK", sentBook);
    },
  },
};
</script>

<style scoped>

</style>