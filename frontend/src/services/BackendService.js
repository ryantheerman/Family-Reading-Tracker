import axios from 'axios';

export default {

  postBook(book) {
      return axios.post('/postBook', book);
  }

}
