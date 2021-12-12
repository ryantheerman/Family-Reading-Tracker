<template>
  <div>
      <add-family v-if="!hasFamily"/>
      <add-fam-members />
  </div>
</template>

<script>
import AddFamily from '../components/AddFamily.vue'
import AddFamMembers from '../components/AddFamMembers.vue'
export default {
  name: 'family',
  data(){
      return {
          
      }
  },
  components: { AddFamily, AddFamMembers },
    created: {
        hasFamily(){
            return this.$store.state.user.familyId == '';
        },
    },
    methods: {
        madeFam(){

        }
    }
}
</script>

<style>

</style>