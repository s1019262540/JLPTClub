<template>
    <header :style="{ backgroundImage: `url(${imgUrl})`, backgroundRepeat:'no-repeat' }" class="JlptHeader">
        <div id="title">{{ title }}</div>
    </header>
</template>

<!-- <script>
    export default {
        data(){
            return{
             title:"JLPT Club",
             imgUrl:require('@/assets/img/sakura.jpg')
            }

        }
    }
</script>

<style scoped>
    div{
        background-image: v-bind('imgUrl');
        height: 100px;
        width: 500px;
    }
</style> -->
<script>
export default {
  data() {
    return {
        title:"JLPT Club",
        imgUrl: require('@/assets/img/biglogo.jpg')
    }
  },
  computed:{
    logo(){
        return this.imgUrl
    }
  }
}
</script>

<style scoped>
    header{
        /* width: 100%;
        height: 200px; 或任意具体值 */
        background-size: cover; /* 关键：让图片填充 */
        
    }
    #title{
        width: 100%;
        border: 10px;
        font-size: xx-large;
        font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif
    }
</style>