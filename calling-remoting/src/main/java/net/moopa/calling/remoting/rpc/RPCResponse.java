package net.moopa.calling.remoting.rpc;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public interface RPCResponse {
    /**
     * 获得返回的 响应代码
     */
    RPCResponseCode getResponseCode();
    void setResponseCode(RPCResponseCode responseCode);

    /**
     * 获得返回的 响应结果
     */
    Object getRPCResult();
    void setRPCResult(Object object);

    /**
     * 获得返回的 server处产生的异常
     */
    Exception getRemoteException();
    void setRemoteException(Exception exception);
}
