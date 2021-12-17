<template>
  <div class="activity-container">
    <div v-for="user in userArray" v-bind:key="user.id">
      <h1  class="user-title">{{ user.username }}'s Reading Activity</h1>
      <table v-if="user.activities.length > 0">
        <tr>
          <th>Book</th>
          <th>Minutes Read</th>
          <th>Date</th>
          <th>Status</th>
          <th>Media Type</th>
        </tr>
        <tr v-for="activity in user.activities" :key="activity.activityId">
          <td>{{ activity.title }}</td>
          <td>{{ activity.minutesRead }}</td>
          <td>{{ activity.dateRead | formatDate }}</td>
          <td v-if="activity.isFinished">Complete</td>
          <td v-else>In Progress</td>
          <td>{{ activity.mediaType }}</td>
        </tr>
      </table>

      <div class="progress-section" v-if="user.prizesArray.length > 0">
        <h1>Progress Toward Prizes</h1>
        <table>
          <tr>
            <th>Prize Name</th>
            <th>Milestone</th>
            <th>Your Minutes</th>
            <th>Percent Complete</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Is Active</th>
          </tr>
          <tr v-for="prize in user.prizesArray" v-bind:key="prize.prizeId">
            <td>{{ prize.prizeName }}</td>
            <td>{{ prize.milestone }}</td>
            <td>{{ prize.minutesRead }}</td>
            <td v-if="prize.minutesRead < prize.milestone">
              {{ Math.round((prize.minutesRead / prize.milestone) * 100) }} %
            </td>
            <td v-else>Goal Reached!</td>
            <td>{{ prize.startDate | formatDate }}</td>
            <td>{{ prize.endDate | formatDate }}</td>
            <td v-if="prize.isActive">Active</td>
            <td v-else>Inactive</td>
          </tr>
        </table>
      </div>
    <div class="progress-card">
      <div class="total">
          <h1>Total Minutes Read:</h1>
          <h2>{{ user.totalMintuesSpentReading }}</h2>
      </div>
      <div class="completed"> 
          <h1>Completed Books:</h1>
          <h2>You have read {{ user.completedBooks.length }} books</h2>
          <div v-for="book in user.completedBooks" :key="book.isbn">
            <h2>{{ book.title }}</h2>
          </div>
      </div>
      <div class="current">
          <h1>Currently Reading:</h1>
          <h2>You are currently reading {{ user.inProgressBooks.length }} books</h2>
          <div v-for="book in user.inProgressBooks" :key="book.isbn">
            <h2>{{ book.title }}</h2>
          </div>     
      </div>
    </div>
          <div class="little-shit"></div>

  </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";
import BackendService from "../services/BackendService";
export default {
  name: "activity",
  data() {
    return {
      userArray: [],
    };
  },

  created() {
    // single user or child
    
    if (
      this.$store.state["user"].familyId == '' ||
      !this.$store.state["user"].isParent
    ) {
      let user = this.$store.state.user;
      user.bookArray = [];
      user.activityArray = [];
      user.activities = [];
      user.totalMintuesSpentReading = 0;
      user.completedBooks = [];
      user.completedBooksTitles = [];
      user.inProgressBooks = [];
      user.prizesArray = [];

      // get activities
      BackendService.getFamilyMembersBooks(user.id).then((response) => {
        if (response.status == 200) {
          user.bookArray = response.data;
          BackendService.getActivitiesById(user.id).then((response) => {
            if (response.status == 200) {
              user.activityArray = response.data;
              user.activityArray.forEach((activity) => {
                user.bookArray.forEach((book) => {
                  if (activity.isbn == book.isbn) {
                    let newActivity = activity;
                    newActivity.title = book.title;
                    user.activities.unshift(newActivity);
                  }
                });
              });

              // get total minutes read
              user.activities.forEach((activity) => {
                user.totalMintuesSpentReading += activity.minutesRead;
              });

              // get completed books
              user.activities.forEach((activity) => {
                if (activity.isFinished == true) {
                  user.completedBooks.push(activity);
                  user.completedBooksTitles.push(activity.title);
                }
              });

              // get in progress books
              user.bookArray.forEach((book) => {
                if (!user.completedBooksTitles.includes(book.title)) {
                  user.inProgressBooks.push(book);
                }
              });

              // if user is a child, get their prizes
              if (!user.isParent) {
                BackendService.getPrizes().then((response) => {
                  if (response.status == 200) {
                    this.$store.commit('ADD_PRIZES_TO_ARRAY', response.data);
                    this.$store.state.prizes.forEach((prize) => {
                      let minTowardPrize = 0;
                      user.activities.forEach((activity) => {
                        if (
                          activity.dateRead >= prize.startDate &&
                          activity.dateRead <= prize.endDate
                        ) {
                          minTowardPrize += activity.minutesRead;
                        }
                      });
                      let newPrize = prize;
                      newPrize.minutesRead = minTowardPrize;

                      user.prizesArray.push(newPrize);
                    });
                  }
                });
              }
              this.userArray.push(user);
            }
          });
        }
      });

      
      console.log(this.userArray.length);

      // parent with family
    } else {
      AuthService.getFamilyMembers().then((response) => {
        if (response.status == 200) {
          this.$store.commit("ADD_USERS_TO_FAMILY_ARRAY", response.data);

          this.$store.state.familyMembers.forEach((user) => {
            user.bookArray = [];
            user.activityArray = [];
            user.activities = [];
            user.totalMintuesSpentReading = 0;
            user.completedBooks = [];
            user.completedBooksTitles = [];
            user.inProgressBooks = [];
            user.prizesArray = [];

            // get books
            BackendService.getFamilyMembersBooks(user.id).then((response) => {
              if (response.status == 200) {
                user.bookArray = response.data;

                // get activities
                BackendService.getActivitiesById(user.id).then((response) => {
                  if (response.status == 200) {
                    user.activityArray = response.data;
                    user.activityArray.forEach((activity) => {
                      user.bookArray.forEach((book) => {
                        if (activity.isbn == book.isbn) {
                          let newActivity = activity;
                          newActivity.title = book.title;
                          user.activities.unshift(newActivity);
                        }
                      });
                    });

                    // get total minutes read
                    user.activities.forEach((activity) => {
                      user.totalMintuesSpentReading += activity.minutesRead;
                    });

                    // get completed books
                    user.activities.forEach((activity) => {
                      if (activity.isFinished == true) {
                        user.completedBooks.push(activity);
                        user.completedBooksTitles.push(activity.title);
                      }
                    });

                    // get in progress books
                    user.bookArray.forEach((book) => {
                      if (!user.completedBooksTitles.includes(book.title)) {
                        user.inProgressBooks.push(book);
                      }
                    });

                   
                    // get prizes
                    BackendService.getPrizes().then((response) => {
                      if (response.status == 200) {
                        this.$store.commit('ADD_PRIZES_TO_ARRAY', response.data);
                        this.$store.state.prizes.forEach((prize) => {
                          let minTowardPrize = 0;
                          user.activities.forEach((activity) => {
                            if (
                              activity.dateRead >= prize.startDate &&
                              activity.dateRead <= prize.endDate
                            ) {
                              minTowardPrize += activity.minutesRead;
                            }
                          });
                          let newPrize = prize;
                          newPrize.minutesRead = minTowardPrize;
                          user.prizesArray.push(newPrize);

                          this.userArray.push(user);
                        });
                      }
                    });
                  }
                });
              }
            });

            
          });
        }
      });
    }
  },
};
</script>

<style >
.activity-container {
  border-top:1px solid rgb(24, 49, 78, .5) ;
  width: 100%;
  /* overflow-y: scroll; */
  position: relative;
  height: auto;
  background-color:white;
  color: rgb(24, 49, 78);
  margin-bottom:500px ;
}

table {
  margin: auto;
}
td,
th {
  padding: 10px;
}
.user-title,
.progress-section{
  text-align: center;
}
tr:nth-child(even) {
  background-color: rgb(24, 49, 78, .2);
  }
tr:nth-child(odd) {background: rgb(255, 255, 255 , .4)}


.total,
.completed,
.current{
  border: 1px solid black;
  border-radius: 10px;
  margin:10px;
  text-align: center;
}

.total,
.completed,
.current{

}

.progress-card{
  display: grid;
  grid-template-columns:1fr 1fr 1fr;
  grid-auto-flow: row;
  grid-template-areas: "total completed current" ;
}
.total{
  grid-area: total;
  margin-left:10px;
}
.completed{
  grid-area:completed;
}
.current{
  grid-area: current;
}
.little-shit {
  margin: 100px;
}
</style>