<template>
  <div class="details">
    <div class="tags">
      <b-button type="is-success" v-on:click="onFavoritedChange">
        <vue-fontawesome
          class=""
          :icon="[this.favorites ? 'fas' : 'far', 'heart']"
        ></vue-fontawesome>
        {{ likesCount }}</b-button
      >

      <router-link v-bind:to="'/photos?userId=' + userId">
        <b-button type="is-success">{{ username }}</b-button>
      </router-link>
    </div>

    <b-image v-bind:src="link"></b-image>
    <h2>{{ caption }}</h2>

    <div class="comment" v-for="comment in comments" v-bind:key="comment.id">
      <div class="author">
        Comment by {{ comment.username }} on {{ comment.dateTime | formatDate }}
      </div>
      {{ comment.message }}
    </div>
  </div>
</template>

<script>
/* line 12 is the query that will show the link for a particular user. If you go to app.view
you'll see there's a link to the photos (photofeed). It's a link to the /Photos without any
filter. Then got to PhotoDetail.vue and line 12 is a link. takes you to the same component as 
photoFeed but instead it's just showing you the photos of a particular user. We go through
the service and the service calls the backend
/? is an http construct. and http url consists of begins with protocol http// then server localhost:8080
// then relative url or path inside of the server. AFter the path, there's a question mark that
allows for query variables. A = B. if there's more than one you'd go /photos?userId= &'
Sends a request of a specific protocol to a specific server , and pass in additional information to that
url. Then we appended the userId to get what we were looking for. Think of it as a name value pair
*/
import photoService from "../services/PhotoService";

export default {
  name: "photo-details",
  props: [],
  data() {
    return {
      caption: "",
      dateTime: null,
      likesCount: 0,
      link: "",
      comments: [],
      username: "",
      userId: null,
      favorites: false,
      isActive: false,
    };
  },
  methods: {
    //This is adding the 'liked' photo to the Favorite's list
    onFavoritedChange() {
      if (!this.favorites) {
        this.favorites = true;
        this.likesCount++;
        photoService.addFavorite(this.photoId).then(() => {
          photoService.like(this.photoId);
        });
      } else {
        this.favorites = false;
        this.likesCount--;
        photoService.removeFavorite(this.photoId).then(() => {
          photoService.unlike(this.photoId);
        });
      }
    },
    //this is for retrieving a single photo. Get of a single photo
    retrievePhoto() {
      photoService.get(this.photoId).then((response) => {
        this.caption = response.data.caption;
        this.dateTime = response.data.dateTime;
        this.link = response.data.link;
        this.likesCount = response.data.likesCount;
        this.userId = response.data.userId;
        this.comments = [];
        photoService.getUsername(this.userId).then((response) => {
          this.username = response.data;
        });
        photoService.getComments(this.photoId).then((response) => {
          this.comments = response.data;
        });
        photoService.getFavorites().then((response) => {
          const foundInResponse = response.data.find((photo) => {
            return photo.id == this.photoId;
          });
          this.favorites = !!foundInResponse;
        });
      });
    },
  },
  created() {
    this.photoId = this.$route.params.id;
    this.retrievePhoto();
  }
};
</script>

<style scoped>
.details {
  max-width: 80%;
  align-self: center;
}
h2 {
  font-size: large;
  font-weight: bold;
  text-align: center;
}
.author {
  font-weight: bold;
}
.tags {
  display: flex;
  justify-content: space-between;
  
}
.button.is-success {
  background-color: #8cc63f;
}
.button.is-info.is-light {
  color: #00adee;
}
.button.is-info.is-light.active {
  background-color: #00adee;
  color: white;
}
</style>
