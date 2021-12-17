<template>
    <div class="actions">
        <button class="createFam"  v-bind:to="familyId" @click.prevent="createFamilyId()">Create Family</button>
    </div>
</template>

<script>
import AuthService from '../services/AuthService';
export default {
    name: "create-family",
    data() {
        return {
            familyId: this.$store.state.user.familyId
        }
    },
    methods: {
        createFamilyId() {
            this.familyId = Math.floor(Math.random() * (10000000 - 100) + 100);
            AuthService.createFamilyId(this.familyId).then(response => {
                if(response.status === 200) {
                    // AuthService.login(this.$store.state['user'])
                    this.$store.state.user.familyId = this.familyId
                    this.$router.push('/');
                    
                }
            });
        }
    }
}
</script>

<style>
.createFam{
    text-align: center;
    height: 140px;
    width:300px;
    border-radius: 10px;
    background-color:  rgb(24, 49, 78, .7);
    border:none;
    transition-duration: 0.3s;
    font-size: 18px;
    align-self:center;
    color: black
}
.createFam:hover{
    background-color:  rgb(24, 49, 78, .6);

}

</style>