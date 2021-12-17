<template>
  <div class="familyLayout">
      <add-family class="addFamily" v-if="$store.state.user.familyId == 0"/>
      <add-fam-members class="addMember" v-else />
      <family-to-reading-list  class="addToRead"/>
      <img id="family" src="../images/image.png">
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


#family{
  grid-area:picture;
  height: 50vh;
  border-radius: 15px;
  margin: 34px auto;
}
.addToRead{
  grid-area: family;
  margin-left: 50px;
}
.addFamily, .addMember {
  grid-area: form; 
  margin-left: 80px;

}
.addFamily{
  margin:auto
}
.familyLayout{
  display: grid;
  grid-template-rows: 200px 1fr;
  grid-template-columns:1fr 1fr ;
  grid-template-areas: "family form"
                        "family  picture";
  row-gap: 10px;
    height: 100vh;
background-color:white ;
}
</style>