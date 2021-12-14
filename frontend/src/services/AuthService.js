import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  createFamilyId(id) {
    return axios.put('/familyId', id, {headers: {'Content-Type': 'application/JSON'}})
  },

  addFamilyMember(username) {
    return axios.put('/addMember', username, {headers: {'Content-Type': 'application/JSON'}});
  },

  getFamilyMembers() {
    return axios.get('/getFamilyMembers');
  }

}
