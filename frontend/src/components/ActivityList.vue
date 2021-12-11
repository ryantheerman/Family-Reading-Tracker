<template>
  <div>

  </div>
</template>

<script>
import BackendService from '../services/BackendService';
export default {

    created() {
        BackendService.getActivities().then(response => {
          //  if(response.status == 200){
                this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data)
                console.log(this.$store.state.activities)
           // }
        })
    }

}
</script>

<style>

</style>