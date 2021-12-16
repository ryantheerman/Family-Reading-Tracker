<template>
    <div class="prizeCard" v-bind:key="prize.prizeName">
        <h3 class="prize-name">Prize: {{ prize.prizeName }}</h3>
        <!-- <p class="prize-description">{{ prize.prizeDescription }}</p> -->
        <!-- <h3 class="milestone">Milestone Goal: {{prize.milestone}}</h3> -->
        <!-- <h3 class="maxPrizes">Max Winners: {{prize.maxPrizes}}</h3> -->
        <h3 class="startDate">Start Date: {{prize.startDate | formatDate}} </h3>
        <h3 class="endDate">End Date: {{prize.endDate | formatDate}} </h3>
        <button class="delete-prize" @click="deletePrize(prize)" v-if="prize.isActive == false">Delete Prize</button>

         <router-link :to="{name: 'edit-prize'}">
      <h3 @click="setActivePrize(prize)" class="selectEdit">Edit Prize</h3> 
      </router-link>
    </div>
</template>
<script>
import BackendService from "../services/BackendService.js"
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
<style >

.prize-name,
.prize-description,
.milestone,
.maxPrizes,
.startDate,
.endDate{
    color: white;
    
}
.prizeCard {
    /* border: 2px solid rgba(162, 94, 73, 0.5); */
    border-radius: 10px;
    width: 350px;
    height: 350px;
    padding-top: 10px;
    margin: 40px;
    text-align: center;
    background-color:rgb(140, 95, 102, 1);
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
    transition-duration: 0.4s;
    font-size: 19px;
}
.prize-name{
    font-size:30px
}
.prizeCard:hover{
    background-color: rgb(140, 95, 102, 0.7);
}
.selectEdit{
    text-align: center;
    height: 30px;
    width: 120px;
    border-radius: 10px;
    background-color: rgb(140, 95, 102);
    border:1px solid white;
    transition-duration: 0.3s;
    font-size: 18px;
    align-self:center;
    color: white;
    padding:5px;
    margin:auto;
}
.delete-prize{
    text-align: center;
    height: 30px;
    width: 120px;
    border-radius: 10px;
    background-color: rgb(140, 95, 102);
    border:1px solid black;
    transition-duration: 0.3s;
    font-size: 18px;
    align-self:center;
    color: black;
    margin-bottom:10px;
}
.delete-prize:hover{
    color: white;
}

</style>