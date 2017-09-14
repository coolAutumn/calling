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
package net.moopa.calling.common.message.clientandmanager;

import net.moopa.calling.common.message.RequestMessageSerializable;
import net.moopa.calling.remoting.protocol.Code;

/**
 * Created by LeeAutumn on 28/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class GetServiceProviderRequestMessage implements RequestMessageSerializable<GetServiceProviderRequestMessage> {
    Code code;
    String serviceName;

    public GetServiceProviderRequestMessage(Code code, String serviceName){
        this.code = code;
        this.serviceName = serviceName;
    }

    public byte[] serialize() {
//        byte[] bs = serviceName.getBytes();
//
//        int length = 1              //code
//                +   bs.length;      //serviceName
//
//        byte[] result = new byte[length];
//        result[0] = code.getCode();
//
//        //fill the serviceName
//        for(int i=0;i<bs.length;i++){
//            result[i+1] = bs[i];
//        }
//        return result;
        return null;
    }

    public GetServiceProviderRequestMessage parse(byte[] bytes) {
        return new GetServiceProviderRequestMessage(Code.getRequestCode(bytes[0]),new String(bytes,1,bytes.length-1));
    }
}
