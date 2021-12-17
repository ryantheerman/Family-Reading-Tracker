<template>
  <div class="prizes">
      <add-prize />
      <display-prizes />
  </div>
</template>

<script>
import AddPrize from '../components/AddPrize.vue';
import DisplayPrizes from '../components/DisplayPrizes.vue';
export default {
    components: { AddPrize, DisplayPrizes }
    
}
</script>

<style>
.prizes{
    background-color: rgb(140, 95, 102, 0.4);
    width: 180vh;
    border-top: 2px solid rgba(140, 95, 102, .5);
}
</style>