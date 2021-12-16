<template>
  <div>
    <label for="search-bar" id="serch-bar-label"
      >
    </label>
    <input
      @keydown.enter="getBooks"
      type="text"
      id="search-bar"
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

<style >
  #search-bar-label{
    margin:7px;
  }

  #search-bar{
    margin-bottom:15px;
    width:100%;
    color: rgb(162, 94, 73, 1);
    /* display:block; */
    border:none;
    padding:15px 20px;
    border-radius:25px;
    background-color: rgba(162, 94, 73, 0.2);
    font-size: 16px;
  }


  #searchTitle,
  #searchAuthor{
  
    margin:10px 0 10px 13px;
    text-align: center;
    height: 40px;
    width:140px;
    border-radius: 10px;
    background-color: rgba(162, 94, 73, 0.8);
    border:none;
    transition-duration: 0.4s;
    font-size: 18px;

  }
  #searchTitle:hover,
  #searchAuthor:hover{
    background-color: rgba(162, 94, 73, 0.6);
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

  .searchBar {
    height: 110vh;
    width: auto;
    position: relative;
    text-align: center;
    color: black;
    margin-bottom: 10vh;
    overflow-y: scroll;
  }
.searchBar::-webkit-scrollbar {
  display: none;
}
</style>