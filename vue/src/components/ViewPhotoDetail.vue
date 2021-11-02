<template>
  <div class="tegram-post">
    <!--User image and username in header-->
    <div class="header level">
      <div class="level-left">
        <figure class="image is-32x32">
          <img :src="post.userImage" />
        </figure>
        <span class="username">{{ post.username }}</span>
      </div>
    </div>
    <!--Main image upload-->
    <div
      class="image-container"
      :class="post.filter"
      :style="{ backgroundImage: 'url(' + post.postImage + ')' }"
    ></div>
    <!--Caption, Likes, username-->
    <div class="content">
      <div class="heart">
        <i class="far fa-heart fa-lg"></i>
      </div>
      <p class="likes">{{ post.likes }} likes</p>
      <p class="caption">
      <router-link v-bind:to="'/photo/' + photo.id">
        <span>{{ post.username }}</span> {{ post.caption }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "PhotoDetails",
  props: {
    post: Object,
  },
};
</script>

<style scoped>
</style>
