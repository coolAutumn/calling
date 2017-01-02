package net.leeautumn.remoting.protocol;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 */
public enum Code {
    //NULL
    NULL(Integer.MAX_VALUE,"NULL"),

    /*between client and manager*/
    GET_SERVICE_PROVIDER(0,"Get_Service_Provider"),
    UPDATE_SERVICE_PROVIDER(1,"Update_Service_Provider"),


    /*between serviceprovider and manager*/


    /*between client and serviceprovider*/
    CALL_SERVICE(3340,"Call_Service"),

    HELLO_SERVICE(3341,"Hello_Service"),


    /*response*/
    RESPONSE_OK(8888,"Response_OK");




    int code = 0x00;
    String codeMeaning = null;

    private static Map<Integer , Code> codeMap = new HashMap<Integer, Code>();
    private static volatile boolean hasRegistered = false;

    Code(int b, String m){
        this.code = b;
        this.codeMeaning = m;
    }

    private static void register(){
        hasRegistered = true;
        for(Code c : Code.values()){
            codeMap.put(c.code,c);
        }
    }

    public static Code getRequestCode(int b){
        if(!hasRegistered){
            register();
        }
        return codeMap.get(b);
    }

    public int getCode() {
        return code;
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }
}
