<template>
  <div>
    <h1 class="title" v-show="!username">Photo Feed</h1>
    <photo-list v-bind:photos="photos" v-bind:username="username" class="photolist"></photo-list>
  </div>
</template>

<script>
import photoList from "../components/PhotoList";
import photoService from "../services/PhotoService";

export default {
  name: "photo-feed",
  components: {
    photoList,
  },
  data() {
    return {
      photos: [],
      username: '',
    };
  },
  created() {
    this.getPhotos();
  },/*
  updated() {
    this.getPhotos();
  },*/
  
  watch: {
    $route(to, from) {
      this.getPhotos();
    }
  },
  methods: {
    getPhotos() {
     // console.log("query = ", this.$route.query);
      let photoPromise;
//  jQuery object with a $ (document.querySelector)
// based upon the query--we used the query parameter so that if we're giving the
// userId, we're only going to fetch the photos for that user, that belong to 
// that user.
// This allows the query parameter to act as a filter.
      if(this.$route.query.userId) {
        // get username
        photoService.getUsername(this.$route.query.userId).then((response) => {
          this.username = response.data;
        });
        photoPromise = photoService.listByUserId(
          this.$route.query.userId);
          // other wise, we're gonna fetch a list of all photos
      } else {
        this.username = '';
        photoPromise = photoService.list();
      }

      photoPromise.then((response) => {
        this.$store.commit("SET_PHOTOS", response.data);
        this.photos = response.data;
      });
    },
  },
};
</script>
<style scoped>
h1 {
  text-align: right;
  color: #8cc63f;
  margin-right: 2rem;
}
</style>