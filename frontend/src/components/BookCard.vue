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
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 350px;
    margin: 20px;
    text-align: center;
    
}

.card .book-title {
    font-size: 1.5rem;
}

.card .book-author {
    font-size: 1rem;
}
</style>