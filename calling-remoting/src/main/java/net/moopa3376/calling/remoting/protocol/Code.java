/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.moopa3376.calling.remoting.protocol;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
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
