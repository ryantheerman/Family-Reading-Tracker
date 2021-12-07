<template>
  <div>
      <button v-on:click="createFamilyId, showForm = true">Create a Family!</button>
    <form v-on:submit.prevent="addNewFamily" >
        <div class="form-element" id="create-family" v-if="showForm === true">
            <label>Name your family: </label>
            <input id="username" type="text" v-model="family.name"/>
        </div>

        <div class="actions">
            <button v-on:click.prevent="showForm = false" type="cancel" v-if="showForm === true">Cancel</button>
            <button v-if="showForm === true">Submit</button>
        </div>
    </form>
  </div>
  
  
  

</template>

<script>
export default {
    name: "create-family",
    data() {
        return {
            showForm: false,
            family: {
                name: '',
                id: 0
            }
        }
    },
    methods: {
        addNewFamily() {

        },

        createFamilyId() {

        }
    }
}
</script>

<style>

</style>