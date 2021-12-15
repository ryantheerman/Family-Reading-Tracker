<template>
  <div>
      <h1>{{book.title}}</h1>
      <h2>{{book.author}}</h2>
      <p>{{book.description}}</p>
      <img v-bind:src="book.thumbnail" />

      <button @click="addBook" v-if="isAdded">Add to collection</button>
      <div v-if="!isAdded">
      <button v-on:click.prevent="showForm === true ? showForm = false : showForm = true">Log Activity</button>
      <form v-show="showForm == true" >
          <label for="minutes" >How many minutes have you spent reading this book?</label>
          <input type="number" name="minutes" placeholder="Minutes read" v-model="activity.minutesRead"/>
          <label for="finished">Have you finished this book?</label>
          <input type="checkbox" name="finished" v-model="activity.isFinished"/>
          <button type="submit" @click.prevent="addActivity">Submit</button>
      </form>
      </div>
  </div>
</template>

<script>
import backendService from '@/services/BackendService';
export default {
    name: 'book-details',
    data() {
        return {
            showForm: false,
            incomingBook: {},
            book: {
                isbn: 0,
                title: '',
                author: '',
                thumbnail: '',
                pageCount: 0,
                description: ''
            },
            activity: {
                isbn: '',
                minutesRead: 0,
                isFinished: false
            }
        }
    },
    created() {
        this.incomingBook = this.$store.state.book;
        this.book.isbn = this.incomingBook.isbn;
        this.book.title = this.incomingBook.title;
        this.book.author = this.incomingBook.author;
        this.book.description = this.incomingBook.description;
        this.book.pageCount = this.incomingBook.pageCount;
        this.book.thumbnail = this.incomingBook.thumbnail;
        this.activity.isbn = this.book.isbn;
    },
    computed: {
        isAdded() {
            let foundBook = this.$store.state.books.find(
                (book) => {
                   return book.isbn == this.book.isbn
                    
        })
        if(foundBook){
            return false;
        }else{
            return true;
        }
        } 
    },
    methods: {
        addBook(){
            backendService.postBook(this.book).then(response => {
                if(response.status === 201) {
                    this.$router.push({name: 'home'});
                    this.$store.commit('WIPE_BOOK');
                }
            });


            
        },
        addActivity(){
            backendService.postActivity(this.activity, this.$store.state.storedUser.id).then(response => {
                if(response.status === 201){
                    this.$router.push({name: 'home'});
                }
            })
        }
    }



}
</script>

<style>

</style>