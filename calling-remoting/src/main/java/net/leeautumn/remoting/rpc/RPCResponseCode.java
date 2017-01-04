package net.leeautumn.remoting.rpc;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public enum RPCResponseCode {
    RESPONSE_OK(1,"OK"),
    RESPONSE_ERROR(2,"Error");


    int code = 0;
    String desc = null;
    RPCResponseCode(int c , String desc){
        this.code = c;
        this.desc = desc;
    }
}
