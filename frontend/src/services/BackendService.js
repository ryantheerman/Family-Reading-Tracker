import axios from 'axios';



export default {

  // Book
  postBook(book) {
      return axios.post('/postBook', book);
  },

  getBooks() {
    return axios.get('/getBookCollection')
  },

  getFamilyMembersBooks(id) {
    return axios.get(`/famMemberBooks/${id}`);
  },

  // Prize
  postPrize(prize) {
    return axios.post('/postPrize', prize);
  },

  getPrizes() {
    return axios.get('/getPrizes');
  },

  editPrize(prize) {
    const http = axios.create({
      headers: {
        'Content-Type': 'application/JSON'
      }
    })
    return http.put("/editPrize", prize);
  },

  // Activity
  postActivity(activity) {
    return axios.post('/postActivity', activity);
  },

  getActivitiesByFam() {
    return axios.get('/getActivityByFam');
  },
  getActivitiesById(id){
    return axios.get(`/getActivityById/${id}`)
  }
}
