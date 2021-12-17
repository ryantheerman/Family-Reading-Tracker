<template>
    <div class="readingList">
       <reading-list />
    </div>
</template>

<script>
import ReadingList from '../components/ReadingList.vue'
export default {
    name: "reading-list-view",
    components: { ReadingList }

}
</script>

<style>
.readingList{
    height: inherit;
}
</style>