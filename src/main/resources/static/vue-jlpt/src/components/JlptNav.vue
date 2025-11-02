<template>
    <nav>
        <a  href="#/{{ item.name }}" v-for="(item) in navList" :key="item.id">{{ item.value }}</a>
        <RouterLink :to="item.name" v-for="(item) in navList" :key="item.id">{{ item.value }}</RouterLink>
    </nav>
</template>

<script>
import { RouterLink } from 'vue-router';

    export default {
        data(){
            return {
                navList:[{'id':1,'name':'search','value':'搜索'},
                {'id':2,'name':'word','value':'单词'},
                {'id':3,'name':'read','value':'阅读'},
                {'id':4,'name':'listen','value':'听力'},
                {'id':5,'name':'test','value':'测试'}]
            }
        }
    }
</script>

<style scoped>
    
    a{
        margin: 20px;
        font-size: larger;
        border: 20px;
        padding:20px;

        
    }
    nav{
        background-color: pink;
        height: 40px;
        width: 100%;
        text-align: center;
    }
</style>