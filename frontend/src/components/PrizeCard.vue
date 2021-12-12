<template>
    <div class="card" v-bind:key="prize.prizeName">
        <h3 class="prize-name">{{ prize.prizeName }}</h3>
        <p class="prize-description">{{ prize.prizeDescription }}</p>
        <h3 class="milestone">{{prize.milestone}}</h3>
        <h3 class="maxWinners">{{prize.maxWinners}}</h3>
        <h3 class="startDate">{{prize.startDate}} </h3>
        <h3 class="endDate">{{prize.endDate}} </h3>
    </div>
</template>
<script>
export default {
    name: 'prize-card',
    props: ['prize'],
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
</style>