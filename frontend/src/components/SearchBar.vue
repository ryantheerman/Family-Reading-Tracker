<template>
  <div >
      <label for="Search bar">Enter the name of the book you'd like to search: </label>
      <input @keydown.enter="getBooks" type="text" name="Search bar" placeholder="Book Title" v-model="searchTerm" />
      <button  @click="getBooksByTitle">Search by Title</button>
      <button  @click="getBooksByAuthor">Search by Author</button>
      <div class="searchBar">
        <div v-for="book in booksArray" v-bind:key="book.isbn" >
            <router-link :to="{name : 'bookDetails'}" >
                <div @click="mutateBook(book)">
            <p v-if="book.volumeInfo.imageLinks">{{book.volumeInfo.title}}</p>
            <img v-if="book.volumeInfo.imageLinks" v-bind:src="book.volumeInfo.imageLinks.thumbnail" />
                </div>
            </router-link>
        </div>
      </div>
  </div>

  
</template>
<script>
import ApiService from '@/services/ApiService.js'
export default {
    data(){
        return{
            searchTerm: '',
            booksArray: []
        }
    },
    methods: {
         getBooksByTitle(){
            ApiService.getByTitle(this.searchTerm).then(response => {
                this.booksArray = response.data.items;
            })
        },
        getBooksByAuthor(){
            ApiService.getByAuthor(this.searchTerm).then(response => {
                this.booksArray = response.data.items;
            })
        },
        mutateBook(book){
            this.$store.commit('SET_BOOK', book)
        }
    }
}
</script>

<style scoped>
    .searchBar {
        overflow-y: scroll;
        height: 400px;
        width: 100%;
        position: fixed;
    }
</style>