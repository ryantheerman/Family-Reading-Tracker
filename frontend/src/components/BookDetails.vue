<template>
  <div>
      <h1>{{book.title}}</h1>
      <h2>{{book.author}}</h2>
      <p>{{book.description}}</p>
      <img v-bind:src="book.thumbnail" />

      <button @click="addBook">Add to collection</button>
  </div>
</template>

<script>
import backendService from '@/services/BackendService';
export default {
    name: 'book-details',
    data() {
        return {
            incomingBook: {},
            book: {
                isbn: 0,
                title: '',
                author: '',
                thumbnail: '',
                pageCount: 0,
                description: ''
            }
        }
    },
    created() {
        this.incomingBook = this.$store.state.book;
        this.book.isbn = this.incomingBook.volumeInfo.industryIdentifiers[0].identifier;
        this.book.title = this.incomingBook.volumeInfo.title;

        this.incomingBook.volumeInfo.authors.forEach(x => {
            this.book.author += x + ' ';
        });
        
        this.book.description = this.incomingBook.volumeInfo.description;
        this.book.pageCount = this.incomingBook.volumeInfo.pageCount;
        this.book.thumbnail = this.incomingBook.volumeInfo.imageLinks.thumbnail;
    },
    methods: {
        addBook(){
            backendService.postBook(this.book).then(response => {
                if(response.status === 201) {
                    this.$router.push({name: 'home'});
                    this.$store.commit('WIPE_BOOK');
                }
            });


            
        }
    }



}
</script>

<style>

</style>