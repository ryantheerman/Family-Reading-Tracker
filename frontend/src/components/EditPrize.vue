<template>
    <div id="form-container">
        <form class="prize-form">
          <label class="prize-name">Prize Name:
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="activePrize.prizeName"
            required
            autofocus
        /> </label>
        <label class="prize-description">Prize Description: 
        <input
            type="text"
            id="prize-description"
            class="form-control"
            placeholder="Description of prize"
            v-model="activePrize.prizeDescription"
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
            v-model="activePrize.milestone"
            required
        /> </label>
        <label class="maxPrizes">Number of Winners: 
        <input
            type="number"
            id="maxPrizes"
            class="form-control"
            placeholder="Number of winners"
            min="1"
            v-model="activePrize.maxPrizes"
            required
        /> </label>
        <label class="startDate">Start Date: 
        <input
            type="date"
            id="startDate"
            class="form-control"
            placeholder=""
            v-model="activePrize.startDate"
            :max="activePrize.endDate"
            required
        /> </label>
        <label class="endDate">End Date: 
        <input
            type="date"
            id="endDate"
            class="form-control"
            placeholder=""
            v-model="activePrize.endDate"
            :min="activePrize.startDate"
            required
        />
        </label>
        <button id="submit-button" v-on:click.prevent="editPrize">Submit Prize!</button>
      </form>
    </div>
</template>

<script>
import BackendService from '../services/BackendService.js';
export default {
    name: 'edit-prize',
    data() {
        return {
    activePrize: {
        prizeName: '',
        prizeDescription: '',
        milestone: '',
        familyId: this.$store.state.user.familyId,
        maxWinners: '',
        startDate: '',
        endDate: '',
        isActive: ''
    }
        }
    },
    methods: {
      editPrize(){
        BackendService.editPrize(this.activePrize).then((response) => {
            if(response.status == 200) {
                this.$router.push("/")
                this.$store.commit("WIPE_ACTIVE_PRIZE");
            }
        });
      }
  },
  created() {
     this.activePrize = this.$store.state.activePrize;
  }
    
}
</script>
