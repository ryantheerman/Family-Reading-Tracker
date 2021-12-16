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
        <button id="submit-button" v-on:click.prevent="saveNewPrize">Submit Prize!</button>
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
.prize-name {
    grid-area: prize-name;
}
#prize-name {
    background-color: rgb(140, 95, 102, 0.4);
    height: 20px;
    border: solid 3px #819C92;
}

#prize-name:hover {
    background-color: rgb(140, 95, 102, 0.2);
}

.prize-description {
    grid-area: prize-description;
}

#prize-description {
    background-color: #BAC9C4;
    height: 20px;
    border: solid 3px #819C92;
}

#prize-description:hover {
    opacity: 75%;
}

.milestone {
    grid-area: milestone;
}

#milestone {
    background-color: #BAC9C4;
    height: 20px;
    border: solid 3px #819C92;
}

#milestone:hover {
    opacity: 75%;
}

.maxPrizes {
    grid-area: maxPrizes;
}

#maxPrizes {
    background-color: #BAC9C4;
    height: 20px;
    border: solid 3px #819C92;
}

#maxPrizes:hover {
    opacity: 75%;
}

.startDate {
    grid-area: startDate;
}

#startDate {
    background-color: #BAC9C4;
    height: 20px;
    border: solid 3px #819C92;
    color: #637E75;
}

#startDate:hover {
    opacity: 75%;
}

.endDate {
    grid-area: endDate;
}

#endDate {
    background-color: #BAC9C4;
    height: 20px;
    border: solid 3px #819C92;
    color: #637E75;
}

#endDate:hover {
    opacity: 75%;
}

#submit-button {
    grid-area: submit-button;
    width: auto;
    background-color: #BAC9C4;
    border: solid 3px #819C92;
    color: #637E75;
}

#submit-button:hover {
    opacity: 75%;
}

.prize-form {
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
}
</style>