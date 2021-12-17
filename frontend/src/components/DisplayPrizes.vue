<template>
  <div class="prize-container">
    <div v-for="prize in $store.state.prizes" v-bind:key="prize.prizeName">
      <router-link :to="{ name: 'prize-details' }">
        <div @click="mutatePrize(prize)">
          <prize-card v-bind:prize="prize" />
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import PrizeCard from "@/components/PrizeCard.vue";
export default {
  components: { PrizeCard },
  name: "display-prizes",
  methods: {
    mutatePrize(prize) {
      this.$store.commit('WIPE_DETAIL_PRIZE');
      this.$store.commit('UPDATE_DETAIL_PRIZE', prize);
    }
  }
};
</script>

<style>
.prize-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 175vh;
  height: 77vh;
  position: relative;
  flex-direction: row;
  /* background-color: rgb(140, 95, 102, 0.4); */

}
</style>