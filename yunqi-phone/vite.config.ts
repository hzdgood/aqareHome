// import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import Components from 'unplugin-vue-components/vite';
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers';
import path from 'path';

const timeStamp = new Date().getTime()

export default defineConfig({
  base: "./",
  plugins: [
    vue(),
    vueJsx(),
    Components({
      resolvers: [
        AntDesignVueResolver({
          importStyle: false, // css in js
        }),
      ],
    }),
  ],
  server: {                // ← ← ← ← ← ←
    host: '0.0.0.0',    // ← 新增内容 ←
    port: 80,
    hmr: true,
    https: false
  },
  build: {
    outDir: 'dist',
    rollupOptions: {
      output: {
        // 入口文件名
        entryFileNames: `assets/[name].${timeStamp}.js`,
        // 块文件名
        chunkFileNames: `assets/[name]-[hash].${timeStamp}.js`,
        // 资源文件名 css 图片等等
        assetFileNames: `assets/[name]-[hash].${timeStamp}.[ext]`,
      }
    }
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  }
})
