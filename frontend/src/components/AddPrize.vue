<template>
  <div>
      <button id="show-form-button" 
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"> Add a Prize</button>
      <form v-on:submit.prevent="addNewPrize" v-if="showForm === true" class="prize-form">
         <div>
          <label class="add-prize-name">Prize Name:
          <input
            type="text"
            id="add-prize-name"
            class="form-control"
            placeholder="Name your prize"
            v-model="newPrize.prizeName"
            required
            autofocus
        /> </label>
        <label class="add-prize-description">Prize Description: 
        <input
            type="text"
            id="add-prize-description"
            class="form-control"
            placeholder="Description of prize"
            v-model="newPrize.prizeDescription"
            required
        /> </label>
        <label class="add-milestone">Minute Milestone: 
        <input
            type="number"
            id="add-milestone"
            class="form-control"
            step="10"
            min="0"
            placeholder="Minutes to Read"
            v-model="newPrize.milestone"
            required
        /> </label>
         </div>
         <div>
        <label class="add-maxPrizes">Number of Winners: 
        <input
            type="number"
            id="add-maxPrizes"
            class="form-control"
            placeholder="Number of winners"
            min="1"
            v-model="newPrize.maxPrizes"
            required
        /> </label>
        <label class="add-startDate">Start Date: 
        <input
            type="date"
            id="add-startDate"
            class="form-control"
            placeholder=""
            v-model="newPrize.startDate"
            required
        /> </label>
        <label class="add-endDate">End Date: 
        <input
            type="date"
            id="add-endDate"
            class="form-control"
            placeholder=""
            v-model="newPrize.endDate"
            required
        />
        </label>
        <button id="save-prize" v-on:click.prevent="saveNewPrize">Save Prize</button>
        </div>    
      </form>
      <div id="prizeInfo" v-if="$store.state.prizes.length == 0">
            <p>Create prizes for your family to compete for here. Prizes should describe what the winner is competing for, the minutes needed to win the prize and the dates your child's minutes read count for. Then its up to them to see whos the winner!</p>
            <p>Once you have your families prizes created you have the ability to view, edit, and delete inactive ones.</p>
      </div>
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
#prizeInfo{
        margin:15px 0 15px 15px;
    text-align: center;
    border:none;
    font-size: 28px;
    align-self:center;
    color: rgb(140, 95, 102);
}

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
#add-prize-name,
#add-prize-description,
#add-milestone,
#add-maxPrizes,
#add-startDate,
#add-endDate {
    background-color: rgb(140, 95, 102, 0.5);
    color:white;
    width: 200px;
    border-radius:10px ;
    margin-bottom:10px;
    transition-duration: .3s;
    /* margin-left: 15px; */
    font-size: 17px;
    border:1px solid white;
    height:40px;
    padding-left:10px ;

}
.add-prize-name,
.add-prize-description,
.add-milestone,
.add-maxPrizes,
.add-startDate,
.add-endDate{
    margin-left: 15px;
    font-size: 19px;
    color: white;
}

#prize-name:hover,
#prize-description:hover,
#milestone:hover,
#maxPrizes:hover,
#startDate:hover,
#endDate:hover {
    background-color: rgb(140, 95, 102, 0.3);
}

#save-prize {
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