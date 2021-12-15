<template>
    <div class="actions">
        <button  v-bind:to="familyId" @click.prevent="createFamilyId()">Create Family</button>
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
                    // AuthService.login(this.$store.state['user'])
                    this.$store.state.user.familyId = this.familyId
                    this.$router.push('/');
                    
                }
            });
        }
    }
};
</script>
<style>
</style>