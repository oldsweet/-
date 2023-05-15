<template>
<div class="logo-container">
    <img src="@/assets/logo.png">
     <h3>南 瓜 云 盘</h3>
</div>
</template>

<script>
export default {
  name: "Logo"
}
</script>

<style scoped>
.logo-container
{

  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}
img{
  height: 80px;
  width: 80px;
  margin-bottom: 20px;
}
h3{
  color: #409EFF;

}
</style>
