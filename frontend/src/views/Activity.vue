<template>
  <div class="activity-page">
    <family-members v-if="$store.state.user.isParent != false && $store.state['user'].familyId != 0" />
    <activity-list v-else />
  </div>
</template>

<script>
import FamilyMembers from "../components/FamilyMembers.vue";
import ActivityList from "../components/ActivityList.vue";
export default {
  components: { FamilyMembers, ActivityList },
  created() {
    this.$store.commit("STORE_SELECTED_USER", this.$store.state['user']);
  },
};
</script>

<style>
.activity-page{
    background-color:rgb(24, 49, 78, 0.2);
    border-top: 1px solid rgb(24, 49, 78, 0.5);
    height:auto;
}

</style>