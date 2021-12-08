<template>
  <div>
      <button id="show-form-button" 
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"> Add a Prize</button>
      <form class="prize-form" v-on:submit.prevent="addNewPrize" v-if="showForm === true">
          <label class="prize-name">Prize Name: 
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="newPrize.prizeName"
            required
            autofocus
        /> </label>
        <label class="prize-description">Prize Description: 
        <input
            type="text"
            id="prize-description"
            class="form-control"
            placeholder="Description of prize"
            v-model="newPrize.prizeDescription"
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
        <button id="submit-button" v-on:click="saveNewPrize()">Save</button> 
        <button id="submit-button" v-on:click="resetPrizeForm()">Cancel</button> 

        <!-- change to a submit input button?? -->
      </form>
      
  </div>
</template>

<script>
import AddPrizeStyle from '../styles/AddPrizeStyle.css'
export default {
    name: "add-prize",
    component: {AddPrizeStyle},
    data() {
        return {
            showForm : false,
            newPrize: {
                prizeName: '',
                prizeDescription: '',
                milestone: '',
                //familyID: 
                maxPrizes: '',
                startDate: '',
                endDate: '',
            },
            prizes:[]
        };
    },
    methods:{
        saveNewPrize(){
            // this.newPrize.milestone = Number.parseInt(this.newPrize.milestone);
            // this.newPrize.maxPrizes = Number.parseInt(this.newPrize.maxPrizes);

            this.prizes.push(this.newPrize);
        
            this.resetPrizeForm();
        },
        resetPrizeForm(){
            this.newPrize = {
                name: '',
                description: '',
                milestone: '',
                maxPrizes: '',
                startDate: '',
                endDate: '',
            }
        },
        // toggleForm(){
        //     this.showForm = !this.showForm
        // }
    }
}
</script>

<style>

</style>