module.exports = {
    lintOnSave: false,
    devServer: {
      disableHostCheck: true,
      port: 8080
    },
    productionSourceMap: false,
    configureWebpack: config => {
      // 为生产环境修改配置...
      if (process.env.NODE_ENV === 'production') {
        config.mode = 'production'
        config.performance = {
          maxEntrypointSize: 10000000,
          maxAssetSize: 30000000
        }
      }
    }
  }