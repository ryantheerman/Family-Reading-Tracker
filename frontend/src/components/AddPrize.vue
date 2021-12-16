<template>
  <div>
      <button id="show-form-button" 
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"> Add a Prize</button>
      <form v-on:submit.prevent="addNewPrize" v-if="showForm === true" class="prize-form">
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
        <button id="submit-button" v-on:click.prevent="saveNewPrize">Save Prize</button>
      </form>
  </div>
</template>
<script>
import backendService from "@/services/BackendService";
// import AddPrizeStyle from '../styles/AddPrizeStyle.css';
export default {
    name: "add-prize",
    component: { backendService},
    data() {
        return {
            showForm : false,
            newPrize: {
                prizeName: '',
                prizeDescription: '',
                milestone: '',
                familyId: this.$store.state.user.familyId,
                maxPrizes: '',
                startDate: '',
                endDate: '',
                isActive: ''

            },

            minStartDate: new Date()
        };
    },
    created(){
        // get all prizes
        backendService.getPrizes().then((response) => {
            this.$store.commit("ADD_PRIZES_TO_ARRAY", response.data);
        });
    },
    methods:{
        saveNewPrize(){
            backendService.postPrize(this.newPrize).then(response => {
                if(response.status === 201) {
                    this.$router.push('/');
                    this.$store.commit("ADD_PRIZES_TO_ARRAY", response.data);
                }
            });
        },
    }
}
</script>
<style>
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
.prize-name,
.prize-description,
.milestone,
.maxPrizes,
.startDate,
.endDate{
    font-size: 20px;   
    margin-left: 15px;
}

#prize-name:hover,
#prize-description:hover,
#milestone:hover,
#maxPrizes:hover,
#startDate:hover,
#endDate:hover {
    background-color: rgb(140, 95, 102, 0.3);
}

#submit-button {
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

/* .prize-form {
    display: grid;
    grid-area: prize-form;
    border: solid 4px #819C92;
    border-radius: 40px;
    background-color: #98AEA6;
    justify-content: center;
    width: 35%;
    margin-top: 20px;
    margin-left: 30px;
    padding-top: 20px;
    padding-bottom: 20px;
    row-gap: 15px;
    color: #FFF8E8;
    grid-template-rows: 40px 40px 40px 40px 40px 40px 40px;
    grid-template-areas: "prize-name"
                         "prize-description"
                         "milestone"
                         "maxPrizes"
                         "startDate"
                         "endDate"
                         "submit-button";
} */
</style>