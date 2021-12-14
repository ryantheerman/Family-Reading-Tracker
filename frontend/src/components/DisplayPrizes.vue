<template>
  <div class="prize-container">
      <div v-for="prize in $store.state.prizes" v-bind:key="prize.prizeName">
          <prize-card
      v-bind:prize="prize"
      />
      </div>
    </div>
</template>

<script>
import PrizeCard from "@/components/PrizeCard.vue";
export default {
  components: { PrizeCard },
  name: "display-prizes",

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