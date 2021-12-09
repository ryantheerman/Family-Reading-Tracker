<template>
    <div class="actions">
        <button v-bind:to="familyId" @click.prevent="createFamilyId()">Create Family</button>
<<<<<<< HEAD
        
=======
>>>>>>> 6ebcdd0fee715143a1bb67bf16f12f588cafff40
    </div>
</template>

<script>
import AuthService from '../services/AuthService';
<<<<<<< HEAD

=======
>>>>>>> 6ebcdd0fee715143a1bb67bf16f12f588cafff40
export default {
    name: "create-family",
    data() {
        return {
            familyId: 0
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