<template>
  <div>
      <add-family />
      <add-fam-members />
  </div>
</template>

<script>
import AddFamily from '../components/AddFamily.vue'
import AddFamMembers from '../components/AddFamMembers.vue'
export default {
    name: 'family',
    components: {AddFamily, AddFamMembers}
}
</script>