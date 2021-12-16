<template>
  <div>
    <div id="prize-details">
      <h1>Prize Details</h1>
      <h3 class="prize-name">{{ prize.prizeName }}</h3>
      <p class="prize-description">{{ prize.prizeDescription }}</p>
      <h3 class="milestone">{{ prize.milestone }}</h3>
      <h3 class="maxPrizes">{{ prize.maxPrizes }}</h3>
      <h3 class="startDate">{{ prize.startDate }}</h3>
      <h3 class="endDate">{{ prize.endDate }}</h3>
    </div>

    <div v-if="prize.isActive">
    <h1>Family Member Progress Toward Prize</h1>


    <div
      id="family-details"
      v-for="member in famMembers"
      v-bind:key="member.id"
    >
      <h3>
        {{ member.username }} has completed
        {{ member.progress }} % of required
        reading to win {{ prize.prizeName }}
      </h3>
    </div>
    </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService.js";
import BackendService from "../services/BackendService.js";
export default {
  name: "prize-details",

  data() {
    return {
      prize: {
        prizeName: "",
        prizeDescription: "",
        milestone: "",
        familyId: this.$store.state.user.familyId,
        maxPrizes: "",
        startDate: "",
        endDate: "",
        isActive: "",
      },
      famMembers: [],
    };
  },

  created() {
    this.prize = this.$store.state.detailPrize;

    AuthService.getFamilyMembers().then((response) => {
      this.$store.commit("ADD_USERS_TO_FAMILY_ARRAY", response.data);
      this.$store.state.familyMembers.forEach((member) => {

        BackendService.getActivitiesById(member.id).then((response) => {
          if (response.status === 200) {
            this.$store.commit("WIPE_ACTIVITIES");
            this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data);

            let minTowardPrize = 0;
            this.$store.state.activities.forEach((activity) => {
              if (
                activity.dateRead >= this.prize.startDate &&
                activity.dateRead <= this.prize.endDate
              ) {
                minTowardPrize += activity.minutesRead;
              }
            });

            member.progress = (minTowardPrize / this.prize.milestone) * 100;
            this.famMembers.push(member);
          }
        });
      });
    });
  },
};
</script>

<style>
</style>

BackendService.getActivitiesById(user.id).then((response) => {
              if (response.status == 200) {
                this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data);
                console.log(this.$store.state.activities);
              }
            });