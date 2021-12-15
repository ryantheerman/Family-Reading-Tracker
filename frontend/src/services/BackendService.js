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

    return axios.put("/editPrize", prize, { headers: {'Content-Type': 'application/JSON'}});
  },

  // Activity
  postActivity(activity, id) {
    return axios.post(`/postActivity/${id}`, activity);
  },
  getActivitiesById(id){
    return axios.get(`/getActivityById/${id}`)
  }
}
