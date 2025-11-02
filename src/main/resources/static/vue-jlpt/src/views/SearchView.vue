<template>
<div>
    <p id="title">{{ title }}</p>
    <div class="search-container">
    <div class="search-box">
      <input @keyup.enter="submit" v-model="word" type="text" placeholder="搜索..." class="search-input">
      <button @click="submit"   class="search-btn">搜索</button>
    </div>
  </div>
</div>
  
</template>

<script>
    export default {
        data(){
            return {
                title:"日本語の辞書",
                word:""
            }
        },
        methods:{
            // getValue(){
            //     this.word=this.refs.search.value
            //    return this.word;
            // },
            submit(){
                // console.log(this.word)
                return this.word;
            }
        }
    }
</script>

<style scoped>
    #title{
        text-align: center;
        font-size:xx-large;
        font-style: oblique;
        color:red;
    }

    .search-container {
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;     /* 垂直居中 */
    height: 10vh;           /* 视口高度 */
    }

    .search-box {
    display: flex;
    gap: 10px;
    }

    .search-input {
    padding: 10px 15px;
    border: 1px solid #ddd;
    border-radius: 4px;
    width: 300px;
    }

    .search-btn {
    padding: 10px 20px;
    background: #f00606;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    }
</style>