<template>
  <div>
      <button id="show-form-button" 
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"> Add a Prize</button>
<<<<<<< HEAD
      <form class="prize-form" v-if="showForm === true">
=======
      <form v-on:submit.prevent="addNewPrize" v-if="showForm === true" class="prize-form">
>>>>>>> ba0947559e20a8781a16ad5f02a50bae213919f5
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
        />
        </label>
<<<<<<< HEAD
        <button v-on:click.prevent="saveNewPrize">Submit Prize!</button>
=======
        <button v-on:click="saveNewPrize()" id="submit-button">Submit Prize!</button>
>>>>>>> ba0947559e20a8781a16ad5f02a50bae213919f5
      </form>
      
  </div>
</template>

<script>
import backendService from "@/services/BackendService";
import AddPrizeStyle from '../styles/AddPrizeStyle.css';
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

            backendService.postPrize(this.newPrize).then(response => {
                if(response.status === 201) {
                    this.$router.push('/');
                }
            });
        },
    }
}
</script>

<style>
</style>