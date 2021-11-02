<template>
  <div id="login">
    <carousel class="carousel" v-bind:photos="photos"></carousel>
    <form class="form-signin" @submit.prevent="login">
      <h1 class="title is-3">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <b-field label="Username" >
        <b-input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
      /></b-field>
      <b-field label="Password" >
        <b-input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
      /></b-field>
      <button class="button is-rounded" type="submit">SIGN IN</button>
      <router-link :to="{ name: 'register' }" class="button is-rounded"
        >NEED AN ACCOUNT?</router-link
      >
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import Carousel from "../components/Carousel";
import photoService from "../services/PhotoService";

export default {
  name: "login",
  components: {
    Carousel,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
      photos: [],
    };
  },
  created() {
    this.getPhotos();
  },
  methods: {
    getPhotos() {
      photoService.getTen().then((response) => {
        this.$store.commit("SET_PHOTOS", response.data);
        this.photos = response.data;
      });
    },
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/Photos");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
.form-signin {
  width: 100%;
  max-width: 400px;
  margin: 1rem;
}

.carousel {
  width: 100%;
  max-width: 700px;
}

#login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  margin-bottom: 10rem;
}

a {
  margin: auto 20px;
  color: white;
  background-color: #00adee;
}

button {
  color: white;
  background-color: #8cc63f;
}

@media only screen and (max-width: 600px) {
  #login {
    flex-direction: column;
  }
}
</style>