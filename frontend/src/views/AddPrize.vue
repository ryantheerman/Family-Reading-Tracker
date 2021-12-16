<template>
  <div>
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

</style>