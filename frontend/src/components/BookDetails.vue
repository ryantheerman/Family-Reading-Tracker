<template>
  <div class="details-container">
        <div class="book-detail">
            <img v-bind:src="book.thumbnail" class="book-cover" />
            <h1 id="book-title" class="book-details">{{book.title}}</h1>
            <h2 id="book-author" class="book-details">{{book.author}}</h2>
            <p id="book-description" class="book-details">{{book.description}}</p>

            <button @click="addBook" v-if="isAdded">Add to collection</button>
            <div v-if="!isAdded">
            <button v-on:click.prevent="showForm === true ? showForm = false : showForm = true">Log Activity</button>
            <form v-show="showForm == true" @submit.prevent="addActivity">
                <label for="minutes" >How many minutes have you spent reading this book?</label>
                <input type="number" name="minutes" placeholder="Minutes read" v-model="activity.minutesRead" min="1" required/>
                <label for="finished">Have you finished this book?</label>
                <input type="checkbox" name="finished" v-model="activity.isFinished"/>
                <select placeholder="media type" v-model="activity.mediaType" required>
                    <option value="Paper">Paper</option>
                    <option value="Digital">Digital</option>
                    <option value="Audiobook">Audiobook</option>
                    <option value="Read-Aloud (Reader)">Read-Aloud (Reader)</option>
                    <option value="Read-Aloud (Listener)">Read-Aloud (Listener)</option>
                    <option value="Other">Other</option>
                </select>
                <button type="submit" >Submit</button>
            </form>
            </div>
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
                isFinished: false,
                mediaType: ''
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

<style >
.details-container{
    color:black;
    background-color:;
    border-top: 2px solid rgba(162, 94, 73, 0.5);
    background-color: rgba(162, 94, 73, 0.3);

}

.book-detail{
    display:grid;
    margin-left:30px;
    font-size:16px;
    grid-template-columns:1fr 2fr;
    grid-template-rows: 1fr 30px 30px;
    grid-template-areas:"cover desc"
                        "title desc"
                        "author desc"
}
img.book-cover {
    grid-area: cover;
    height:60vh;
    margin-top: 15px;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);

}
.book-title{
    grid-area: title;
    /* align-self: center; */
}
.book-author{
    grid-area: author;
}
.book-description{
    grid-area: desc;
}
button{
    grid-area: button;
}

</style>