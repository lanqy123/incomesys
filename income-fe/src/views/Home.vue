<template>
  <div class="content">
    <slot>
      
    </slot>
  </div>
</template>

<script>
  export default {
    name: "home",
    props: {
      isHome: {
        type: Boolean,
        default: true
      }
    },
    data() {
      return {
      }
    },
    mounted() {
    },
    components: {},
    methods: {}
  }
</script>

<style scoped="scoped">
  .my-text-ident{
    text-indent:30px;
  }
  .my-second-subject .my-text-ident{
    text-indent:60px;
  }
  .my-top-title{
    font-size: 20px;
    font-weight: bold;
    margin: 20px 0px 30px 30px;
  }
</style>
<style>
  .my-title .el-collapse-item__header{
    font-size: 15px;
    padding-left: 30px;
    font-weight: bold;
  }
  .my-title.my-second-subject .el-collapse-item__header{
    padding-left: 60px;
  }
</style>
