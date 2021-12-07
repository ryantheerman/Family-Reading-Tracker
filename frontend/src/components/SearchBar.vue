<template>
  <div>
      <label for="Search bar">Enter the name of the book you'd like to search: </label>
      <input type="text" name="Search bar" placeholder="Book Title" v-model="searchTerm" />
      <button  @click="getISBNs">Search</button>
      <div v-for="book in ISBNresults" v-bind:key="book.isbn" >
          <p>{{book.volumeInfo.title}}</p>
      </div>
  </div>
</template>
<script>
import ApiService from '@/services/ApiService.js'
export default {
    data(){
        return{
            searchTerm: '',
            ISBNresults: []
        }
    },
    methods: {
         getISBNs(){
            ApiService.searchForISBN(this.searchTerm).then(response => {
                this.ISBNresults = response.data.items;
            })
        }  
    }
}
</script>
<style>
</style>