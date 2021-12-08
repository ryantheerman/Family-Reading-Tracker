<template>
  <div>
      <form class="prize-form">
          <label class="prize-name">Prize Name: 
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="newPrize.name"
            required
            autofocus
        /> </label>
        <label class="prize-description">Prize Description: 
        <input
            type="text"
            id="prize-description"
            class="form-control"
            placeholder="Description of prize"
            v-model="newPrize.description"
            required
        /> </label>
        <label class="milestone">Minute Milestone: 
        <input
            type="number"
            id="milestone"
            class="form-control"
            step="10"
            min="0"
            placeholder="Minutes to Read"
            v-model="newPrize.milestone"
            required
        /> </label>
        <label class="maxPrizes">Number of Winners: 
        <input
            type="number"
            id="maxPrizes"
            class="form-control"
            placeholder="Number of winners"
            min="1"
            v-model="newPrize.maxPrizes"
            required
        /> </label>
        <label class="startDate">Start Date: 
        <input
            type="date"
            id="startDate"
            class="form-control"
            placeholder=""
            v-model="newPrize.startDate"
            required
        /> </label>
        <label class="endDate">End Date: 
        <input
            type="date"
            id="endDate"
            class="form-control"
            placeholder=""
            v-model="newPrize.endDate"
            required
        /> </label>
        <button id="submit-button">Submit Prize!</button>
      </form>
      
  </div>
</template>

<script>
import AddPrizeStyle from '../styles/AddPrizeStyle.css';
export default {
    name: "add-prize",
    component: {AddPrizeStyle},
    data() {
        return {
            newPrize: {
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