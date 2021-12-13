<template>
  <div>
      <div v-for="activity in activities" :key="activity.activityId">
          <h2>{{activity.bookName}}</h2>
      </div>
  </div>
</template>

<script>
import BackendService from '../services/BackendService';
export default {
    data(){
        return {
            activities : []
        }
    },
    created() {
        BackendService.getActivitiesById(this.$store.state['user'].id).then(response => {
                this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data)
               // console.log(this.$store.state.activities);
               
               for(let i = 0; i < this.$store.state.activities.length; i++) {
                   
                      for(let b = 0; b < this.$store.state.books.length; b++){
                        if(this.$store.state.activities[i].isbn == this.$store.state.books[b].isbn){
                            let newAct = this.$store.state.activities[i];
                            newAct.bookName = this.$store.state.books[b].title;
                            this.activities.push(newAct);
                        }
                      }
               }
               
                     
                
                
        });
        
        }
        
    }


</script>

<style>

</style>