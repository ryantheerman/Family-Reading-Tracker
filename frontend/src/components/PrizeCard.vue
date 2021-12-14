<template>
    <div class="card" v-bind:key="prize.prizeName">
        <h3 class="prize-name">{{ prize.prizeName }}</h3>
        <p class="prize-description">{{ prize.prizeDescription }}</p>
        <h3 class="milestone">{{prize.milestone}}</h3>
        <h3 class="maxPrizes">{{prize.maxPrizes}}</h3>
        <h3 class="startDate">{{prize.startDate}} </h3>
        <h3 class="endDate">{{prize.endDate}} </h3>
         <router-link :to="{name: 'edit-prize'}">
      <h4 @click="setActivePrize(prize)">select to edit</h4> 
      </router-link>
      <button class="delete-prize" @click="deletePrize(prize)" v-if="prize.isActive == false">Delete Prize</button>
    </div>
</template>
<script>
import BackendService from '../services/BackendService.js'
export default {
    name: 'prize-card',
    props: ['prize'],
     methods: {
      setActivePrize(prize){
          this.$store.commit("SET_ACTIVE_PRIZE", prize);
      },
      deletePrize(prize){
          BackendService.deleteSelectedPrize(prize).then((response) => {
            if(response.status == 204){
            this.$store.commit("DELETE_PRIZE");
            this.$router.push('/');
            }
         })
        }
      }
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