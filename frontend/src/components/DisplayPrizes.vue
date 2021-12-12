<template>
  <div class="prize-container">
      <!-- <a v-on:click="editPrize()"> -->
      <prize-card
      v-bind:prize="prize"
      v-for="prize in $store.state.prizes"
      v-bind:key="prize.prizeName"
      />
      <!-- </a> -->
    </div>
</template>

<script>
import PrizeCard from "@/components/PrizeCard.vue";
// import BackendService from '../services/BackendService';
export default {
  components: { PrizeCard },
  name: "display-prizes",
//   methods: {
//       editPrize(){
//         const prizeToEdit = { prizeName: this.prizeName, prizeDescription: this.prizeDescription, milestone: this.milestone, maxWinners: this.maxWinners, startDate: this.startDate, endDate: this.editPrize};
//         BackendService.editPrize(this.prizeName, prizeToEdit).then((response) => {
//             if(response.status == 200) {
//                 this.$router.push("/");
//             }
//         });
//       }
//   },
//   created() {
//       BackendService.getPrize(this.prizeName).then((response) => {
//           this.$store.commit("SET_ACTIVE_PRIZE", response.data);
//           this.prizeName = response.data.prizeName;
//       })
//   }

};
</script>

<style>
.prize-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 100%;
  overflow-y: scroll;
  position: relative;
  height: 90vh;
}
</style>