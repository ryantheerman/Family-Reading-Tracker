<template>
    <div class="prizeCard" v-bind:key="prize.prizeName">
        <h3 class="prize-name">Prize: {{ prize.prizeName }}</h3>
        <!-- <p class="prize-description">{{ prize.prizeDescription }}</p> -->
        <h3 class="milestone">Milestone Goal: {{prize.milestone}}</h3>
        <h3 class="maxPrizes">Max Winners: {{prize.maxPrizes}}</h3>
        <h3 class="startDate">Start Date: {{prize.startDate | formatDate}} </h3>
        <h3 class="endDate">End Date: {{prize.endDate | formatDate}} </h3>
         <router-link :to="{name: 'edit-prize'}">
      <h3 @click="setActivePrize(prize)" class="selectEdit">Select to Edit</h3> 
      </router-link>
    </div>
</template>
<script>
export default {
    name: 'prize-card',
    props: ['prize'],
     methods: {
      setActivePrize(prize){
          this.$store.commit("SET_ACTIVE_PRIZE", prize);
      }
     }
}
</script>
<style>
.prizeCard {
    /* border: 2px solid rgba(162, 94, 73, 0.5); */
    border-radius: 10px;
    width: 350px;
    height: 350px;
    padding-top: 10px;
    margin: 20px;
    text-align: center;
    background-color:rgb(140, 95, 102);
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
    transition-duration: 0.4s;
    font-size: 19px;
}
.prizeCard:hover{
    background-color: rgb(140, 95, 102, 0.7);
}
.selectEdit{
    color:black;
}
</style>