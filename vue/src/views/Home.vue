<template>
  <div id="photo-page">
    <div id="photo-chooser" class="">
      <button class="upload-button" v-on:click="picFile">Add Photo</button>
      <input
        hidden
        type="file"
        id="file"
        ref="file"
        v-on:change="handleFileUpload"
      />
    </div>

    <div id="photo-display">
      <div id="vertical-align">
        <div>
          <img id="imageView" />
        </div>
        <div id="caption-box" v-show="files != null">
          <label
            >Add Photo Caption
            <textarea
              id="caption"
              name="caption"
              rows="1"
              maxlength="156"
              wrap="hard"
              v-if="files != null"
              v-on:change="handleCaptionChanged"
              v-model="caption"
            ></textarea>
          </label>
          <button id="submit-btn" v-on:click.prevent="submitFile">
            Click to Submit Photo
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import PhotoService from "../services/PhotoService";
import s3Service from "../services/S3Service";
export default {
  /*
      Defines the data used by the component
*/
  data() {
    return {
      files: null,
      caption: "",
    };
  },
  methods: {
    /*
        Submits the file to the server
        async chains promises - calls a chain
        of then functions, returns promise in the callbacks
        in the then functionS3Service.js
        
        WHEN WE HIT SUBMIT: IN services/S3Service.js
         // creates a URL which is presigned to accept
            // an HTTP put that contains that file.
            return photoService.getPresignedUrl(`${username}/${file.name}`, file.type)
                .then((response) => {
                    //all we're doing inside here getting back that final URL
                    // of the Picture(in view/Home.vue)
                    const http = axios.create({
                        
                        baseURL: response.data,
                    });
                    delete http.defaults.headers.common['Authorization'];
      */

    // calls the file click above
    picFile() {
      this.$refs.file.click();
    },

/* uploaded photo to s3, s3 gave the url address to s3 api for the photo
/get back response.
*/
    async submitFile() {
      try {
        // upload this photo to S3
        const s3Resp = await s3Service.uploadToS3("aphoward", this.files[0]);
        /*
           gather the photo metadata (photo_id, fileName, link, caption)
           the following consts gives us each part of the data to send to our database.
           user ID from VUEX store--gets our current user -- see in inspect window, it returns a user id
          */
        const userId = this.$store.state.user.id;
        // fileName
        const fileName = this.files[0].name;
        // S3 api: get the S3 link from the response
        // inspect: link shows url +? and then more chars. parsed at ? to get aws url to picture
        const link = s3Resp.config.baseURL.split("?")[0];
        const caption = this.caption;

        const photoMetadata = {
          userId,
          fileName,
          link,
          caption,
        };

        // send the metadata to the backend to store in PosgresSQL DB
        console.log("sending photo metadata", photoMetadata);
        const metaResp = await PhotoService.create(photoMetadata);
        this.$router.push("/Photos");
        console.log("SUCCESS!!", link);
      } catch (err) {
        console.log("FAILURE!!", err);
      }
    },
    /*
        Handles a change on the file upload
      */

    handleFileUpload(event) {
      //console.log('event', event)
      this.files = event.target.files; // the file array from the file selection event

      // find the dom element representing the imageview--this is what we want to upload
      // browser javascript for finding elementById. It represents the root of the
      // document. The DOM model for that page.
      // HTML page is parsed into a structure in memory called DOM(Document Object Model)
      // Tree-like heirarchal structure starting at the root element (URL), containing
      // 2 children: the head and the body. In the body, you have all of those elements.
      const imageView = document.getElementById("imageView");

      // setting the imageview src to a constructed url that represents the first selected file
      //  this cause the browser to "upload" this file, and show it in the imageview
      imageView.src = URL.createObjectURL(this.files[0]);

      // now bind a listener to listen for the loaded event
      imageView.onLoad = () => {
        // free the constructed url to avoid memory leak
        URL.revokeObjectURL(output.src);
      };
    },

    //  Handles a change to the caption

    handleCaptionChanged(event) {
      this.caption = event.target.value;
    },
  },
};
</script>
<style scoped>
#photo-page {
  color: white;
  height: 2000px;
  background-color: #00adee;
}
#photo-chooser {
  text-align: center;
  margin: 20px auto;
  padding: 20px 0;
    margin: 2rem 1rem 1rem 1rem;

}
#file-label {
  padding-left: 100px;
}
#photo-display {
  text-align: center;
  position: relative;
}
#imageView {
  height: 475px;
  width: auto;
  grid-template-columns: repeat(auto-fill, minmax(500px, 1fr));
  margin: 0 2rem 2rem 2rem;
}
#caption-box {
  max-width: 550px;
  width: auto;
  margin: 0 auto;
  padding: 5px 0; 
}
.upload-button {
  padding: 0 33px;
  border-radius: 9px;
  background-color: #8cc63f;
  color: white;
  text-transform: uppercase;
  height: 50px;
}
#caption-box textarea {
  background-color: white;
  text-align: center;
  font-size: x-large;
  font-weight: bold;
  padding: 20px 0px 20px 0px;
   border-radius: 10px;
}
#caption {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  width: 100%;
  display: block;
  margin: auto;
  
}
#caption-box label {
  font-size: large;
  font-weight: bold;
}
#submit-btn {
  color: white;
  background-color: #8cc63f;
  padding: 10px;
  margin: 10px;
  border-radius: 10px;
}
#vertical-align {
  
  padding-top: 1%;

}
</style>
