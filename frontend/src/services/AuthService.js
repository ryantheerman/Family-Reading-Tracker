import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  createFamilyId(id) {
    const http = axios.create({
      headers: {
        'Content-Type': 'application/JSON'
      }
    })
    return http.put('/familyId', id)
  },

  addFamilyMember(username) {
    console.log(username + " " + typeof(username));
    const http = axios.create({
      headers: {
        'Content-Type': 'application/JSON'
      }
    })
    return http.put('/addMember', username);
  },

  getFamilyMembers() {
    return axios.get('/getFamilyMembers');
  }

}
