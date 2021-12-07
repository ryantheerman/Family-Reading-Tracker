import axios from 'axios';
const http = axios.create({
    baseURL: 'https://www.googleapis.com/books/v1/volumes?q=',
    headers: {
        common: {
        }
    }
})
export default{
    searchForISBN(searchTerm){
        return http.get(`${searchTerm}&maxResults=30`)
    }
}