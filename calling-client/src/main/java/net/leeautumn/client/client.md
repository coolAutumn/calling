##Design thoughts:
* 通过Client工厂来根据一个接口来创建一个service,用户直接调用这个service对象来调用接口中的service就能够获得结果.
这个工厂需要用户提供service接口和服务器地址即可.
* Client工厂创建出来的其实是一个代理对象,用户通过这个代理对象来调用 '真实'的方法 .


##执行步骤:
1. 用户通过ClientFactory来创建服务对象.
2. 用户通过服务对象来调用服务.
3. 服务对象的代理(即ClientFactory创建的对象)来进行与远程服务端通信,获得结果(其实相当于调用对象方法).
    - 首先根据在ClientFactory中的参数来指定service server
    - 尝试连接,并且发送 ProtocolMessage 注意set一下Message的Body中的RPCRequest对象
    - 得到返回结果,解析得到MessageBody中RPCResponse中的result
    - 并且作为结果返回
4. client调用得到结果,rpc调用结束

