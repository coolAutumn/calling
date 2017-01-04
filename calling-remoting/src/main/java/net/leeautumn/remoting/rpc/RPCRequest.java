package net.leeautumn.remoting.rpc;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public interface RPCRequest{
    /**
     * 获得请求的服务类
     */
    String getServiceClassName();
    void setServiceClassName(String serviceClassName);

    /**
     * 获得 请求的方法描述       -       server端利用这个来找到相应的方法
     */
    String getMethodDesc();
    void setMethodDesc(String methodDesc);

    /**
     * 获得 请求的参数
     */
    Object[] getArgs();
    void setArgs(Object[] args);
}
