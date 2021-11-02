<template>
  <div class="list">
<!--    <b-tag class="title" type="is-success" size="is-large" v-show="username">Photo Feed</b-tag> -->
    <h1 class="title" v-show="username">{{ username }}'s Photos</h1>
    <section class="photolist">
      <div v-for="photo in photos" v-bind:key="photo.id">
        <router-link v-bind:to="'/photo/' + photo.id">
        <b-tag type="is-success" class="like">&#10084; {{ photo.likesCount }}</b-tag>
          <b-image
            class="photo"
            v-bind:src="photo.link"
            webp-fallback=".jpg"
            ratio="16by9"
          ></b-image>
          <p class="caption">{{ photo.caption }}</p>
        </router-link>
        <div v-show="photo.commentUsername" class="comment">
          <div class="author">
            Most recent comment by {{ photo.commentUsername }} on
            {{ photo.commentDateTime | formatDate}}
          </div>
          {{ photo.message }}
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "photo-list",
  props: ["photos", "username"],
  data() {
    return {};
  },
};
</script>
<style scoped>
.photolist {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(500px, 1fr));
  grid-gap: 2rem;
  padding: 0 1rem 1rem 1rem;
}
.comment {
  padding: 8px;
}
.comment .author {
  font-style: italic;
  border-bottom: 1px solid #999;
  margin-bottom: 8px;
}
.caption {
  font-weight: bold;
  text-align: center;
}
.list {
  display: inline-block;
  text-align: right;
}
.username {  
  margin: 1rem;
}
.tag.is-success {
  background-color: #8cc63f;
  font-size: large;
  font-weight: bold;
}
.like {
  display: flex;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
h1 {
  text-align: right;
  color: #8cc63f;
  margin-right: 2rem;
}
</style>