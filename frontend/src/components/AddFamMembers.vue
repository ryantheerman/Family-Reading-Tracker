<template>
  <div class="whatever">
    <form class="formstuff">
      <label class="addMembers"
        >Enter family member usernames
        <input
          type='text'
          id="members"
          v-model="familyMember.username"
          required
        />
      </label>
      <button id="addMemButton" @click.prevent="addMember">Add Family Member</button>
    </form>

  </div>
</template>

<script>
import AuthService from '../services/AuthService';
export default {
    name: 'add-fam-members',
    data() {
        return {
            familyMember: {
                username: ''
            }
            
        }
    },
    methods: {
        addMember() {
            AuthService.addFamilyMember(this.familyMember.username).then(response => {
                if(response.status === 200) {
                  this.$router.push('/');
                }
            });
        }
    }
};
</script>

<style>
#addMemButton{
    margin:auto;
    text-align: center;
    height: 40px;
    width:auto;
    border-radius: 10px;
    background-color:  rgb(24, 49, 78, .7);
    border:none;
    transition-duration: 0.3s;
    font-size: 18px;
    align-self:center;
    color: black
}
.addMembers{
  color: rgb(24, 49, 78);
}
#members{
    background-color:  rgb(24, 49, 78, .2);
    color:white;
    width: 200px;
    border-radius:10px ;
    margin-bottom:10px;
    transition-duration: .3s;
    /* margin-left: 15px; */
    font-size: 17px;
    border:1px solid white;
    height:40px;
    padding-left:10px ;
}

#your-family {
  text-align: center;
  margin-left: 20px;
  /* text-align: left; */
  color: rgb(24, 49, 78);
  }

#fam-links {
  text-align: center;
   border: 1px solid  rgb(24, 49, 78, .6);
   width:300px;
   border-radius:25px;
   margin:10px auto;
    background-color: rgb(24, 49, 78, .3)

   /* color:white; */
}
#fam-links:hover{
  background-color: rgb(24, 49, 78, .6)

}
.username-link{
   /* border: 1px solid  rgb(24, 49, 78, .6); */
   width:auto;
   /* border-radius:25px; */
   color:white;
}
.username-link:hover{
  color: white;
}

</style>