Design thoughts:

- 自动发现用户的service provider.


运行流程
-------

- 通过ServiceServer来启动服务端
- 向ServiceServer注册来 添加 Service
- 接收客户端发来的请求,根据请求代码来映射到相应的服务
- 在映射到相关服务之前,还需要经过filter chain
- 之后将ChannelHandlerContext和byte数组传给用户自己定义好的ServiceProvider
- 之后过程由用户自己定义
