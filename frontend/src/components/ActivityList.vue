<template>
  <div>
      <div v-for="activity in $store.state.activities" :key="activity.activityId">
          <h2>{{activity.minutesRead}}</h2>
      </div>
  </div>
</template>

<script>
import BackendService from '../services/BackendService';
export default {

    created() {
        BackendService.getActivities().then(response => {
                this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data)
                console.log(this.$store.state.activities)
        })
    }

}
</script>

<style>

</style>