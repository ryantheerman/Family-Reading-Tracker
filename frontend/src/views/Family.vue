<template>
  <div>
<<<<<<< HEAD
      <add-family />
=======
      <add-family v-if="!hasFamily"/>
>>>>>>> 8d516b3ae2edc23de6c31f785be40d74c02ecf12
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