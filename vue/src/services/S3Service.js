import axios from 'axios';
import photoService from './PhotoService';



export default {
 
    uploadToS3(username, file) {
        if (file) {
            // creates a URL which is presigned to accept
            // andHTTP put that contains that file.
            return photoService.getPresignedUrl(`${username}/${file.name}`, file.type)
                .then((response) => {
                    //all we're doing inside here getting back that final URL
                    // of the Picture(in view/Home.vue)
                    const http = axios.create({
                        
                        baseURL: response.data,
                    });
                    delete http.defaults.headers.common['Authorization'];

                    return http.put("", file, {
                        headers: {
                            'Content-Type': file.type,
                        }
                    });
                })
        }
    }
}