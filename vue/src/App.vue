<template>
  <div id="app">
    <div id="nav">
      <img class="navbar-brand" src="../public/mark-blue.jpg" alt="logo" />
      <div class="navbar">
        <router-link v-bind:to="{ name: 'home' }" class="navbar-item"
        v-if="$store.state.token != ''"
          >Upload Photo</router-link
        >
        <router-link v-bind:to="{ name: 'photo-feed' }" class="navbar-item"
          >Photos</router-link
        >
        <router-link v-bind:to="{ name: 'favorites' }" class="navbar-item"
        v-if="$store.state.token != ''"
          >Favorites</router-link
        >
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          class="navbar-item"
          >Logout</router-link
        >
      </div>
      <img class="navbar-icon" src="../public/TEGram.png" alt="logo" />
    </div>
    <router-view />
  </div>
</template>
// use input textbox under the picture--after upload photo it pops up , we type
something // same click event as submit // 2 different tasks talk to aws, send
the photo to aws s3, get the link from aws // link to the photo which we are
storing in the database, and then send all this info // to the data base. link
file name and caption. //??/ andrews // elena created photoRequest // send from
client to server, not send whole photo class, which // something to think
about-generate frandom file name, on serve name generate the name there. //
environment variables for aws

<style scoped>
.img {
  margin-left: 0px;
}
#app {
  height: 100vh;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  position: relative;
}
#nav {
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding-left: 20px;
  padding-bottom: 10px;
  margin-top: 25px;
}
.navbar {
  display: inline;
  position: relative;
  align-items: center;
  padding-top: 35px;
}
.navbar-brand {
  display: inline;
  padding-bottom: 5px;
  margin-top: 15px;
  padding: 0.1em 0.1em;
  white-space: nowrap;
  height: 70px;
  max-width: 200px;
  margin-right: 20px;
}
.navbar-item {
  display: inline;
  max-width: 0em;
  padding: 1.2em 1.2em;
  border-radius: 9px;
  background-color: #00adee;
  max-height: 4em;
  margin: 0.4em 0.4em;
  color: white;
  text-transform: uppercase;
  white-space: nowrap;
  margin-top: 15px;
  height: 70px;
}
.navbar-item:hover {
  background-color: #8cc63f;
  color: white;
}

.navbar-icon {
  display: inline;
  height: auto;
  max-width: 300px;
}
.navbar-stackx {
  display: flex;
  justify-content: center;
  margin-left: 20%;
  padding: auto;
}

@media screen and(max-width: 970) {

   .navbar-item {
    margin: 0px 20px;

    padding-right: 30px;
    padding-left: 30px;
    padding-top: 10px;
    padding-bottom: 10px;
    border-radius: 9px;
    background-color: #00adee;
    max-height: 3em;
    margin: 0.3em 0.3em;
    color: white;
    text-transform: uppercase;
    font-size: large;
    white-space: nowrap;
    margin-top: 15px;
    height: 20px;
  }

  .navbar-icon {
    max-width: 150px;
  }
  .navbar-brand {
    max-height: 40px;
  }
}
@media screen and (max-width: 910px) {
  #app {
    font-size: 10px;
  }
  #nav {
    margin-top: 25px;
  }
  .navbar-item {
    margin: 0px 20px;

    padding-right: 30px;
    padding-left: 30px;
    padding-top: 15px;
    padding-bottom: 15px;
    border-radius: 9px;
    background-color: #00adee;
    max-height: 3em;
    margin: 0.3em 0.3em;
    color: white;
    text-transform: uppercase;
    font-size: large;
    white-space: nowrap;
    margin-top: 15px;
    height: 20px;
  }

  .navbar-icon {
    max-width: 150px;
  }
  .navbar-brand {
    max-height: 40px;
  }
}

@media screen and (max-width: 895px) {
  #app {
    font-size: 10px;
  }
 
  .navbar-item {
    padding-right: 30px;
    padding-left: 30px;
    padding-top: 20px;
    padding-bottom: 20px;
    border-radius: 9px;
    background-color: #00adee;
    max-height: 5em;
    margin: 0.9em 0.9em;
    color: white;
    text-transform: uppercase;
    font-size: medium;
    white-space: nowrap;
    margin-top: 25px;
    height: 20px;

  }

  .navbar-icon {
    max-width: 180px;
  }
  .navbar-brand {
    max-height: 40px;
  }

  @media screen and (max-width: 650px) {
    #app {
      font-size: 13px;
    }
    .navbar {
        padding-top: 15px;

    }
    .navbar-icon {
      max-width: 85px;
    }
    .navbar-brand {
      max-height: 50px;
    }
    .navbar-item {
      padding: 0.1em 0.1em;
      border-radius: 9px;
      background-color: #00adee;
      max-height: 2em;
      margin: 0.4em 0.4em;
      color: white;
      text-transform: uppercase;
      white-space: nowrap;
      padding-top: 10px;
      padding-bottom: 10px;
      margin-top: 15px;
      height: 20px;
    }
  }
  @media screen and (min-width: 450px) {
    #app {
      font-size: 10px;
    }
    .navbar {
        padding-top: 25px;

    }
    .navbar-icon {
      max-width: 95px;
    }
    .navbar-brand {
      max-width: 45px;
      padding-bottom: 5px;
    }
    .navbar-item {
      padding-top: 12px;
      padding-bottom: 12px;
      border-radius: 9px;
      background-color: #00adee;
      max-height: 1em;
      color: white;
      text-transform: uppercase;
      white-space: nowrap;
      padding-right: 5px;
      padding-left: 5px;
      height: 10px;
      font-size: small;
    }
  }
  @media screen and (min-width: 350px) {
    #app {
      font-size: 10px;
    }
    .navbar {
        padding-top: 25px;

    }

    .navbar-icon {
      max-width: 95px;
    }
    .navbar-brand {
      max-width: 45px;
      padding-bottom: 5px;
    }
    .navbar-item {
      padding-top: 12px;
      padding-bottom: 12px;
      border-radius: 9px;
      background-color: #00adee;
      max-height: 1em;
      color: white;
      text-transform: uppercase;
      white-space: nowrap;
      padding-right: 10px;
      padding-left: 10px;
      height: 10px;
      font-size: small;
    }
  }
}
</style>
