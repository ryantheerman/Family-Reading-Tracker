<template>
    <div class="actions">
        <button v-bind:to="familyId" @click.prevent="createFamilyId()" v-if="this.familyId == 0">Create Family</button>
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
                    this.$router.push('/');
                }
            });
        }
    }
}
</script>

<style>

</style>