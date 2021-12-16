<template>
  <div>
    <form>
      <label class="addMembers"
        >Enter family member usernames
        <input
          type='text'
          id="members"
          v-model="familyMember.username"
          required
        />
      </label>
      <button @click.prevent="addMember">Add Family Member</button>
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
</style>