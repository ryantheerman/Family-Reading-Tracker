<template>
    <div class="editPrizes" id="form-container">
        <form class="prize-form">
          <label class="edit-prize-name">Prize Name:
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="activePrize.prizeName"
            required
            autofocus
        /> </label>
        <label class="edit-prize-description">Prize Description: 
        <input
            type="text"
            id="prize-description"
            class="form-control"
            placeholder="Description of prize"
            v-model="activePrize.prizeDescription"
            required
        /> </label>
        <label class="edit-milestone">Minute Milestone: 
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
        <label class="edit-maxPrizes">Number of Winners: 
        <input
            type="number"
            id="maxPrizes"
            class="form-control"
            placeholder="Number of winners"
            min="1"
            v-model="activePrize.maxPrizes"
            required
        /> </label>
        <label class="edit-startDate">Start Date: 
        <input
            type="date"
            id="startDate"
            class="form-control"
            placeholder=""
            v-model="activePrize.startDate"
            :max="activePrize.endDate"
            required
        /> </label>
        <label class="edit-endDate">End Date: 
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
        <button id="update-button" v-on:click.prevent="editPrize">Submit Prize!</button>
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
<style >

#show-form-button{
    margin:15px 0 15px 15px;
    text-align: center;
    height: 40px;
    width:140px;
    border-radius: 10px;
    background-color: rgb(140, 95, 102, 0.7);
    border:none;
    transition-duration: 0.3s;
    font-size: 18px;
    align-self:center;
    color: black
}
#show-form-button:hover{
    background-color: rgb(140, 95, 102, 0.9);
    color:white
}
#prize-name,
#prize-description,
#milestone,
#maxPrizes,
#startDate,
#endDate {
    background-color: rgb(140, 95, 102, 0.5);
    color:white;
    width: 300px;
    border-radius:10px ;
    margin-bottom:10px;
    transition-duration: .3s;
    margin-left: 15px;
    font-size: 1.15rem;
}
.edit-prize-name,
.edit-prize-description,
.edit-milestone,
.edit-maxPrizes,
.edit-startDate,
.edit-endDate{
    font-size: 20px;   
    margin-left: 15px;
    color:black;
}

#prize-name:hover,
#prize-description:hover,
#milestone:hover,
#maxPrizes:hover,
#startDate:hover,
#endDate:hover {
    background-color: rgb(140, 95, 102, 0.3);
}

#update-button {
    margin:15px 0 15px 15px;
    text-align: center;
    height: 40px;
    width:140px;
    border-radius: 10px;
    background-color: rgb(140, 95, 102, 0.7);
    border:none;
    transition-duration: 0.3s;
    font-size: 18px;
    align-self:center;
    color: black
}

#submit-button:hover {
    background-color: rgb(140, 95, 102, 0.3);
}

/* .editPrizes{
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  width: 100%;
  position: relative;
  /* height:110vh */
  /*
  background-image:cover ;
  background-color: rgb(140, 95, 102, 0.4);

} */


</style>