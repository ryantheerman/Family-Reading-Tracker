<template>
  <div>
      <button id="show-form-button" 
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"> Add a Prize</button>
      <form v-on:submit.prevent="addNewPrize" v-if="showForm === true">
          <label>Prize Name: 
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="newPrize.name"
            required
            autofocus
        /> </label>
        <label>Prize Description: 
        <input
            type="text"
            id="prize-description"
            class="form-control"
            placeholder="description of prize"
            v-model="newPrize.description"
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
            v-model="newPrize.milestone"
            required
        /> </label>
        <label>Number of Winners: 
        <input
            type="number"
            id="maxPrizes"
            class="form-control"
            placeholder="Number of Prizes Available"
            min="1"
            v-model="newPrize.maxPrizes"
            required
        /> </label>
        <label>Start Date: 
        <input
            type="date"
            id="startDate"
            class="form-control"
            placeholder=""
            v-model="newPrize.startDate"
            required
        /> </label>
        <label>End Date: 
        <input
            type="date"
            id="endDate"
            class="form-control"
            placeholder=""
            v-model="newPrize.endDate"
            required
        /> </label>
        <button v-on:click="saveNewPrize()">Submit Prize!</button> 
        <!-- change to a submit input button?? -->
      </form>
      
  </div>
</template>

<script>
export default {
    name: "add-prize",
    data() {
        return {
            showForm : false,
            newPrize: {
                name: '',
                description: '',
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