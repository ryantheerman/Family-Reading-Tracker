<template>
  <div>
      <h1>{{book.title}}</h1>
      <h2>{{book.author[0]}}</h2>
      <p>{{book.description}}</p>
      <img v-bind:src="book.thumbnail" />

      <button @click="addBook">Add to collection</button>
  </div>
</template>

<script>
export default {
    name: 'book-details',
    data() {
        return {
            incomingBook: {},
            book: {
                isbn: 0,
                title: '',
                author: [],
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
        this.book.author = this.incomingBook.volumeInfo.authors;
        this.book.description = this.incomingBook.volumeInfo.description;
        this.book.pageCount = this.incomingBook.volumeInfo.pageCount;
        this.book.thumbnail = this.incomingBook.volumeInfo.imageLinks.thumbnail;
    },
    methods: {
        addBook(){
            this.$router.push({name: 'home'});
            this.$store.commit('WIPE_BOOK')
        }
    }



}
</script>

<style>

</style>