<template>
  <section>
    <b-carousel>
      <b-carousel-item v-for="(photo, i) in photos" :key="i">
        <section class="hero">
          <div class="hero-body">
            <b-image
              class="photo"
              v-bind:src="photo.link"
              ratio="16by9"
              webp-fallback=".jpg"
            ></b-image>
          </div>
        </section>
      </b-carousel-item>
    </b-carousel>
    <br />
  </section>
</template>

<script>
export default {
  name: "carousel",
  props: ["photos"],
  data() {
    return {
      photo: {
        link: "",
        caption: "",
      },
    };
  },
};
</script>
<style scoped>
.photo {
  min-width: 400px;
}
</style>