<template>
  <div>
    <h1>{{ $store.state.storedUser.username }}'s Reading Activity</h1>
    <div v-for="activity in activities" :key="activity.activityId">
      <h2>{{ activity.bookName }}</h2>
    </div>
  </div>
</template>

<script>
import BackendService from "../services/BackendService";
export default {
  data() {
    return {
      activities: [],
    };
  },
  created() {
    let id = this.$store.state.storedUser.id;
    BackendService.getFamilyMembersBooks(id).then((response) => {
      if (response.status === 200) {
        this.$store.commit("WIPE_BOOKS_ARRAY");
        this.$store.commit("ADD_BOOKS_TO_ARRAY", response.data);

        BackendService.getActivitiesById(id).then((response) => {
          this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data);
          for (let i = 0; i < this.$store.state.activities.length; i++) {
            for (let b = 0; b < this.$store.state.books.length; b++) {
              if (
                this.$store.state.activities[i].isbn ==
                this.$store.state.books[b].isbn
              ) {
                let newAct = this.$store.state.activities[i];
                newAct.bookName = this.$store.state.books[b].title;
                this.activities.push(newAct);
              }
            }
          }
        //   this.$store.commit("WIPE_STORED_USER");
        });
      }
    });

    this.$store.commit("WIPE_ACTIVITIES");
  },
};
</script>

<style>
</style>