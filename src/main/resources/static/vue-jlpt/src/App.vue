<template>
  <div>
      <!-- <JlptHeader></JlptHeader>
      <JlptNav></JlptNav>
      <SearchView></SearchView> -->
    <TestPage></TestPage>
  </div>
</template>

<script>
// import { component } from 'vue/types/umd';
// import JlptHeader from './components/JlptHeader.vue';
// import JlptNav from './components/JlptNav.vue';
// import SearchView from './views/SearchView.vue';
// import WordView from './views/WordView.vue';
import TestPage from './views/TestPage.vue';
//   const routers=[{
//     path:'#/search',
//     component:SearchView
//   },
//   {
//     path:'#/word',
//     component:WordView
//   }
// ]
  export default {
    components:{
      // JlptHeader,
      // JlptNav,
      // SearchView,
      // WordView
      TestPage
    }
  }
</script>

<style>
    
</style>
