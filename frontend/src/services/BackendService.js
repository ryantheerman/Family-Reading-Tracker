import axios from 'axios';

export default {

  postBook(book) {
      return axios.post('/postBook', book);
  },

  getBooks() {
    return axios.get('/getBooks')
  },

  postPrize(prize) {
    return axios.post('/postPrize');
  },

  getPrizes() {
    return axios.get('/getPrizes');
  },

  postActivity(activity) {
    return axios.post('/postActivity', activity);
  },

  getActivities() {
    return axios.get('/getActivities');
  }

}
