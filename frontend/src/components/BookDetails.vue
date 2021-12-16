<template>
  <div class="details-container">
        <div class="book-detail">
            <img v-bind:src="book.thumbnail" class="book-cover" />
            <h1 id="book-title" class="book-details">{{book.title}}</h1>
            <h2 id="book-author" class="book-details">{{book.author}}</h2>
            <p id="book-description" class="book-details">{{book.description}}</p>

            <button id="collection-button" @click="addBook" v-if="isAdded">Add to collection</button>
            <div v-if="!isAdded">
            <button id="log-button" v-on:click.prevent="showForm === true ? showForm = false : showForm = true">Log Activity</button>
            <form class="log-form" v-show="showForm == true" @submit.prevent="addActivity">
                <label id="minutes-label" for="minutes" >How many minutes have you spent reading this book?</label>
                <input id="minutes-read" type="number" name="minutes" placeholder="Minutes read" v-model="activity.minutesRead" min="1" required/>
                   <label id="finish-label" for="finished">
                    <input  id="finished" type="checkbox" name="finished" v-model="activity.isFinished"/>
                    Have you finished this book?</label>
                <label id="media-label" for="media-type" >Select Media Type: </label>
                <select id="media-type"  placeholder="Media Type" v-model="activity.mediaType" required>
                    <option class="option" value="Paper">Paper</option>
                    <option class="option" value="Digital">Digital</option>
                    <option class="option" value="Audiobook">Audiobook</option>
                    <option class="option" value="Read-Aloud (Reader)">Read-Aloud (Reader)</option>
                    <option class="option" value="Read-Aloud (Listener)">Read-Aloud (Listener)</option>
                    <option class="option" value="Other">Other</option>
                </select>
                <button id="submit-button" type="submit" >Submit</button>
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
    border-top: 2px solid rgba(162, 94, 73, 0.5);
    background-color: rgba(162, 94, 73, 0.3);
    background-size: cover;

}

.book-detail{
    display:grid;
    margin-left:20px;
    margin-right: 40px;
    font-size:16px;
    grid-template-columns:1fr 2fr;
    grid-template-rows: 40px 40px auto auto;
    grid-template-areas:"cover title"
                        "cover author"
                        "cover desc"
                        "cover button";
    column-gap: 10px;
    height: 87vh;
                        
}

img.book-cover {
    grid-area: cover;
    height:60vh;
    margin-top: 15px;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);

}
#book-title{
    grid-area: title;
    /* align-self: center; */
}
#book-author{
    grid-area: author;
}
#book-description{
    grid-area: desc;
}
#log-button,
/* #submit-button, */
#collection-button{
    grid-area: button;
}

#collection-button{
    width :150px;
    height:40px;
}
/* l{
    display: flex;
    align-items:left;
    background-color: red;
    padding: 0;
} */
#log-button{
  
    margin:10px 0;
    text-align: center;
    height: 40px;
    width:140px;
    border-radius: 10px;
    background-color: rgba(162, 94, 73, 0.8);
    border:none;
    transition-duration: 0.4s;
    font-size: 18px;

}

#log-button:hover{
    background-color: rgba(162, 94, 73, 0.6);
}

  #minutes-label{
    margin-bottom:17px;
    font-size: 18px;
  }

  #minutes-read{
    margin-left:15px;
    margin-bottom:15px;
    width:10%;
    color: rgb(162, 94, 73, 1);
    /* display:block; */
    border:none;
    padding:15px 20px;
    border-radius:25px;
    background-color: rgba(162, 94, 73, 0.4);
    font-size: 16px;
  }

 #finish-label {
    font-size: 18px;
    display: block;
    padding-left: 15px;
    text-indent: -15px;
}
 #finished {
    display: inline;
    width: 15px;
    height: 15px;
    padding: 0;
    margin: 0;
    vertical-align: bottom;
    position: relative;
    top: -1px;
    font-size: 18px;
 }
 #media-type {
    background-color: rgba(162, 94, 73, 0.5);
    color:white;
    width: 300px;
    height: 40px;
    border-radius:10px ;
    margin:40px 0;
    transition-duration: .3s;
    font-size: 1.15rem;
    border: none;
    padding-left: 10px;
}
 #media-label {
    width: 300px;
    height: 40px;
    border-radius:10px ;
    transition-duration: .3s;
    font-size: 1.15rem;
    border: none;
    /* padding-left: 10px; */
}
#media-type:hover{
    border: 1px solid white;

}
#submit-button{
    margin:10px ;
    text-align: center;
    height: 40px;
    width:140px;
    border-radius: 10px;
    background-color: rgba(162, 94, 73, 0.8);
    border:none;
    transition-duration: 0.4s;
    font-size: 18px;
}
.option:hover{
    background-color: rgba(162, 94, 73, 0.1);

}

</style>