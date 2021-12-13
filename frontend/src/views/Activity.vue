<template>
  <family-members v-if="$store.state.user.isParent != false && $store.state['user'].familyId != 0" />
  <!-- ($store.state.user.isParent != false && $store.state['user'].familyId != 0) -->
  <activity-list v-else />
</template>

<script>
import FamilyMembers from "../components/FamilyMembers.vue";
import ActivityList from "../components/ActivityList.vue";
export default {
  components: { FamilyMembers, ActivityList },
  created() {
    this.$store.commit("STORE_SELECTED_USER", this.$store.state['user']);
    console.log(this.$store.state['user'].familyId)
  },
};
</script>

<style>
</style>