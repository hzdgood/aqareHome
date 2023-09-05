<template>
  <main>
    HOME
    <div class="wrapper">
      <nav>
        <RouterLink to="/workSheet">我的工单</RouterLink>
        <RouterLink to="/project">我的项目</RouterLink>
        <RouterLink to="/measure">测量单</RouterLink>
        <RouterLink to="/write">核销</RouterLink>
        <RouterLink to="/commission">提成</RouterLink>
      </nav>
    </div>
    <RouterView></RouterView>
  </main>
</template>

<script setup lang="ts">

</script>
