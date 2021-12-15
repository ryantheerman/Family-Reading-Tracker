<template>
  <div>
      <add-family v-if="$store.state.user.familyId == 0"/>
      <add-fam-members v-else />
      <family-to-reading-list />
  </div>
</template>
<script>
import AddFamily from '../components/AddFamily.vue'
import AddFamMembers from '../components/AddFamMembers.vue'
import FamilyToReadingList from '../components/FamilyToReadingList.vue'
export default {
  name: 'family',
  data(){
      return {
          
      }
  },
  components: { AddFamily, AddFamMembers, FamilyToReadingList  },
    // created: {
    //     hasFamily(){
    //         return this.$store.state.user.familyId == '';
    //     },
    // },
    methods: {
        madeFam(){
        }
    }
}
</script>
<style>
</style>