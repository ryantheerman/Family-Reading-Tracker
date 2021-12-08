import axios from 'axios';
const http = axios.create({
    baseURL: 'https://www.googleapis.com/books/v1/volumes?q=',
    headers: {
        common: {
        }
    }
})
export default{
    getByTitle(searchTerm){
        return http.get(`intitle:${searchTerm}&maxResults=40`)
    },
    getByAuthor(searchTerm){
        return http.get(`inauthor:${searchTerm}&maxResults=40`)
    }
}