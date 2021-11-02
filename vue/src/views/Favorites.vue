<template>
  <div>
    <h1 class="title">Favorites</h1>
    <photo-list v-bind:photos="photos" class="photolist"></photo-list>
  </div>
</template>

<script>
import photoList from "../components/PhotoList";
import photoService from "../services/PhotoService";

export default {
  name: "favorites",
  components: {
    photoList,
  },
  data() {
    return {
      photos: [],
    };
  },
  created() {
    this.getPhotos();
  },

  methods: {
    getPhotos() {
      let photoPromise;
      photoPromise = photoService.listFavoritesByUser(this.$route.query.userId);

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