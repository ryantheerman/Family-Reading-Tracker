<template>
  <div id="activity-container">
    <h1>Progress Toward Prizes</h1>
    <table >
      <tr>
        <th>Prize Name</th>
        <th>Milestone</th>
        <th>Your Minutes</th>
        <th>Percent Complete</th>
        <th>Start Date</th>
        <th>End Date</th>
      </tr>
      <tr  v-for="prize in prizesArray" v-bind:key="prize.prizeId"  >
        <td> {{prize.prizeName}} </td>
        <td> {{prize.milestone}} </td>
        <td> {{prize.minutesRead}} </td>
        <td v-if="prize.minutesRead < prize.milestone" > {{ Math.round((prize.minutesRead/prize.milestone)*100) }} % </td>
        <td v-else> Goal Reached! </td>
        <td> {{prize.startDate | formatDate}} </td>
        <td> {{prize.endDate | formatDate}} </td>
      </tr>
    </table>

    <h1>{{ $store.state.storedUser.username }}'s Reading Activity</h1> 
    <table>
      <tr>
        <th>Book</th>
        <th>Minutes Read</th>
        <th>Date</th>
        <th>Status</th>
      </tr>
    <tr v-for="activity in activities" :key="activity.activityId">
      <td>{{ activity.bookName }}</td>
      <td>{{ activity.minutesRead }}</td>
      <td>{{ activity.dateRead | formatDate }}</td>
      <td v-if="activity.isFinished">Complete</td>
      <td v-else>In Progress</td>
    </tr>
    </table>

    <h1>Total Minutes Read:</h1>
    <h2>{{ totalMintuesSpentReading }}</h2>


    <h1>Completed Books:</h1>
    <h2>You have read {{completedBooks.length}} books</h2>
    <div  v-for="book in completedBooks" :key="book.isbn">
      <h2>{{ book.bookName }}</h2>
    </div>

    <h1>Currently Reading:</h1>
    <h2>You are currently reading {{inProgressBooks.length}} books</h2>
    <div  v-for="book in inProgressBooks" :key="book.isbn">
      <h2>{{ book.title }}</h2>
    </div>
  </div>
</template>

<script>
import BackendService from "../services/BackendService";

export default {
  data() {
    return {
      activities: [],
      prizesArray:[],
      completedBooks: [],
      completedBooksTitles: [],
      inProgressBooks: [],
      totalMintuesSpentReading: 0,
    };
  },

  created() {

    this.completedBooks = [];
    let id = this.$store.state.storedUser.id;
    BackendService.getFamilyMembersBooks(id).then((response) => {
      if (response.status === 200) {
        this.$store.commit("WIPE_BOOKS_ARRAY");
        this.$store.commit("ADD_BOOKS_TO_ARRAY", response.data);

        BackendService.getActivitiesById(id).then((response) => {
          this.$store.commit("ADD_ACTIVITIES_TO_ARRAY", response.data);
          for (let i = 0; i < this.$store.state.activities.length; i++) {
            for (let b = 0; b < this.$store.state.books.length; b++) {
              if (
                this.$store.state.activities[i].isbn ==
                this.$store.state.books[b].isbn
              ) {
                let newAct = this.$store.state.activities[i];
                newAct.bookName = this.$store.state.books[b].title;
                this.activities.push(newAct);
              }
            }
          }


          this.activities.forEach(activity => {
            this.totalMintuesSpentReading += activity.minutesRead;
          });

          this.activities.forEach(activity => {
                  if(activity.isFinished == true) {
                    this.completedBooks.push(activity);
                    this.completedBooksTitles.push(activity.bookName);
                  } 
                });
                console.log(this.completedBooksTitles);

          this.$store.state.books.forEach(book => {
            if(!this.completedBooksTitles.includes(book.title)) {
              this.inProgressBooks.push(book);
            }
          });

         
          this.$store.state.prizes.forEach(prize => {
            let minTowardPrize = 0;
            this.activities.forEach(activity => {
              if((activity.dateRead >= prize.startDate) && (activity.dateRead <= prize.endDate)){
                minTowardPrize += activity.minutesRead;
              }
            })
              let newPrize = prize ;
            newPrize.minutesRead = minTowardPrize;
         
                this.prizesArray.push(newPrize);
            
                 });
          

          console.log(this.prizesArray);
          // compare milestone to read minute but only within dates     
        });
      }
    });

    this.$store.commit("WIPE_ACTIVITIES");
  },
};
</script>

<style>

#activity-container {
  width: 100%;
  overflow-y: scroll;
  position: relative;
  height: 82vh;
}
table{
  margin: auto;
}
td,
th{
  padding: 10px;
}

</style>