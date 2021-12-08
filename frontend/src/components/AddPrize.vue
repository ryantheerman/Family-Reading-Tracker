<template>
  <div>
      <form>
          <label>Prize Name: 
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="prize.name"
            required
            autofocus
        /> </label>
        <label>Prize Description: 
        <input
            type="text"
            id="prize-description"
            class="form-control"
            placeholder="description of prize"
            v-model="prize.description"
            required
        /> </label>
        <label>Minute Milestone: 
        <input
            type="number"
            id="milestone"
            class="form-control"
            step="10"
            min="0"
            placeholder="Minutes to Read"
            v-model="prize.milestone"
            required
        /> </label>
        <label>Number of Winners: 
        <input
            type="number"
            id="maxPrizes"
            class="form-control"
            placeholder="Number of Prizes Available"
            min="1"
            v-model="prize.maxPrizes"
            required
        /> </label>
        <label>Start Date: 
        <input
            type="date"
            id="startDate"
            class="form-control"
            placeholder=""
            v-model="prize.startDate"
            required
        /> </label>
        <label>End Date: 
        <input
            type="date"
            id="endDate"
            class="form-control"
            placeholder=""
            v-model="prize.endDate"
            required
        /> </label>
        <button>Submit Prize!</button>
      </form>
      
  </div>
</template>

<script>
export default {
    name: "add-prize",
    data() {
        return {
            prize: {
                name: '',
                description: '',
                milestone: '',
                //familyID: 
                maxPrizes: '',
                startDate: '',
                endDate: '',
            }
        }
    }
}
</script>

<style>

</style>