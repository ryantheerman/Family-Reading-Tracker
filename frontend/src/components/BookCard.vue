<template>
    <div class="card" v-bind:key="book.isbn">
        <h2 class="book-title">{{ book.title }}</h2>
        <img v-bind:src="book.thumbnail" />
        <h3 class="book-author">{{ book.author }}</h3>
    </div>
</template>

<script>
export default {
    name: 'book-card',
    props: ['book'],
}
</script>

<style>
.card {
    /* border: 2px solid rgba(162, 94, 73, 0.5); */
    border-radius: 10px;
    width: 250px;
    height: 350px;
    margin: 20px;
    text-align: center;
    background-color: rgba(162, 94, 73, 1);
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
    transition-duration: 0.4s;

}
.card:hover{
    background-color: rgba(162, 94, 73, 0.5);

}

.book-title {
    font-size: 1.5rem;
    color: rgba(162, 94, 73, 1);
    /* margin-top: 93px; */
    padding-top:7px;
}

.book-author {
    font-size: 1rem;
    color: rgba(162, 94, 73, 1);
}
</style>