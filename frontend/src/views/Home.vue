<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div id="nav">
      <router-link v-bind:to="{name: 'create-family'}" class="create-family"> Parent Page </router-link>
      &nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'add-prize'}" class="add-prize"> Add a Prize </router-link>
    </div>
    
  
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
